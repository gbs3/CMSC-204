import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.HashSet;
import javax.swing.JOptionPane;

public class ChatServer implements Runnable 
{ // Modify class header to implement Runnable
    private String name;
    private Socket clientSocket;
    private BufferedReader in;
    private PrintWriter out;

    ChatServer(int port) 
    {
        CHAT_ROOM_PORT = port;
    }
    
    /**
     * The port that the server listens on.
     */
    private static int CHAT_ROOM_PORT;

    /**
     * The set of all names of clients in the chat room.  Maintained
     * so that we can check that new clients are not registering name
     * already in use.
     */
    private static HashSet<String> names = new HashSet<String>();

    /**
     * The set of all the print writers for all the clients.  This
     * set is kept so we can easily broadcast messages.
     */
    private static HashSet<PrintWriter> writers = new HashSet<PrintWriter>();
    
    @Override // Override the run method from Runnable interface
    public void run() 
    {
        ServerSocket listener = null;
        try 
        {
            //TODO STUDENT: Create a server socket
            listener = new ServerSocket(CHAT_ROOM_PORT);
            System.out.println("The chat server is running.");
            while (true) 
            {
                //TODO STUDENT: Listen for a client to join
                clientSocket = listener.accept();
                // Setup input and output channels
                in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
                out = new PrintWriter(clientSocket.getOutputStream(), true);

                // Request a name from this client.  Keep requesting until
                // a name is submitted that is not already used.  Note that
                // checking for the existence of a name and adding the name
                // must be done while locking the set of names.
                name = null;
                while (name == null) 
                {
                    out.println("SUBMITNAME");
                    name = in.readLine();
                    if (name.equals("")) 
                    {
                        name = null;
                    } 
                    else if (name.equals("null")) 
                    {
                        name = null;
                    } 
                    else if (names.contains(name)) 
                    {
                        out.println("WRONGNAME");
                        Thread.sleep(100);
                        name = null;
                    } else 
                    {
                        names.add(name);
                    }
                }

                // Now that a successful name has been chosen, add the
                // socket's print writer to the set of all writers so
                // this client can receive broadcast messages and start a
                out.println("NAMEACCEPTED");
                writers.add(out);

                // new server thread to handle the client
                ServerThreadForClient svrForClient = new ServerThreadForClient(in, out, name);
                Thread t = new Thread(svrForClient);
                t.start();
            }
        } 
        catch (IOException e) 
        {
            e.printStackTrace();
        }
        catch (InterruptedException e) 
        {
            e.printStackTrace();
        } 
        finally 
        {
            try 
            {
                if (listener != null) 
                {
                    listener.close();
                }
            } 
            catch (IOException e) 
            {
                e.printStackTrace();
            }
        }
    }
            
    private class ServerThreadForClient implements Runnable 
    {
        BufferedReader in;
        PrintWriter out;
        String name;
        
        ServerThreadForClient(BufferedReader in, PrintWriter out, String name) 
        {
            this.in = in;
            this.out = out;
            this.name = name;
        }
        
        @Override
        public void run() 
        {
            try 
            {
                // Accept messages from this client and broadcast them.
            	// Ignore other clients that cannot be broadcasted to.
                while (true) 
                {
                    String input = in.readLine();
                    if (input == null) 
                    {
                        return;
                    }
                    for (PrintWriter writer : writers) 
                    {
                        writer.println("MESSAGE " + name + ": " + input);
                    }
                }
            } 
            catch (IOException e) 
            {
                e.printStackTrace();
            } 
            finally 
            {
            	// This client is going down!  Remove its name and its print
                // writer from the sets, and close its socket.
                if (name != null) 
                {
                    names.remove(name);
                }
                if (out != null) 
                {
                    writers.remove(out);
                }
                try 
                {
                    clientSocket.close();
                } 
                catch (IOException e) 
                {
                    e.printStackTrace();
                }
            }
        }
    }
}

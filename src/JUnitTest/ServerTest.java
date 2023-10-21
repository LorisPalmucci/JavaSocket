package JUnitTest;

import org.junit.jupiter.api.Assertions;
import socket.Server;

import java.net.Socket;

public class ServerTest {

    Server s = new Server();
    Socket c = new Socket();

    public void isOpen(){
        Assertions.assertTrue(s.checkIsClosed());
    }

}

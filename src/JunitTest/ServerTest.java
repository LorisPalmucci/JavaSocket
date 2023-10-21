package JunitTest;
import org.junit.jupiter.api.Assertions;
import socket.Server;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Assertions.*;

import java.net.Socket;

public class ServerTest {

    Server s = new Server();
    Socket c = new Socket();

    @Test
    public void isOpen(){
        Assertions.assertTrue(s.checkIsClosed());
    }

}

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.IOException;
import java.net.Socket;

public class ClientModeller {
    
    private Socket socket;
    private BufferedInputStream bis;
    private BufferedOutputStream bos;
    private String name;


    public ClientModeller(Socket socket, BufferedInputStream bis, BufferedOutputStream bos, String name) throws IOException {
        this.socket = socket;
        this.bis = new BufferedInputStream(socket.getInputStream());
        this.bos = new BufferedOutputStream(socket.getOutputStream());
        this.name = name;
    }


    public BufferedInputStream getBis() {
        return bis;
    }


    public BufferedOutputStream getBos() {
        return bos;
    }


    public String getName() {
        return name;
    }


    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        ClientModeller other = (ClientModeller) obj;
        if (socket == null) {
            if (other.socket != null)
                return false;
        } else if (!socket.equals(other.socket))
            return false;
        if (bis == null) {
            if (other.bis != null)
                return false;
        } else if (!bis.equals(other.bis))
            return false;
        if (bos == null) {
            if (other.bos != null)
                return false;
        } else if (!bos.equals(other.bos))
            return false;
        if (name == null) {
            if (other.name != null)
                return false;
        } else if (!name.equals(other.name))
            return false;
        return true;
    }

    




}

package model;

import java.io.IOException;
import java.net.ServerSocket;

public class PortNumber {
    // 동적으로 할당된 포트 번호를 저장합니다.
    public static int PORT = findAvailablePort();
    
    /*
     * 사용 가능한 포트를 찾는 메서드입니다.
     * 포트를 지정하지 않고 ServerSocket을 생성하면 시스템이 자동으로 비어 있는 포트를 할당합니다.
     * 
     * @return 사용 가능한 포트 번호
     */
    private static int findAvailablePort() {
        int port;
        try (ServerSocket socket = new ServerSocket(0)) {
            port = socket.getLocalPort();
        } catch (IOException e) {
            throw new RuntimeException("사용 가능한 포트를 찾을 수 없습니다", e);
        }
        return port;
    }
}

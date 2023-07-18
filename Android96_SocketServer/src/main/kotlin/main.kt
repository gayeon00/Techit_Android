import java.io.DataInputStream
import java.io.DataOutputStream
import java.net.ServerSocket

fun main() {

    //사용할 포트 번호
    val portNumber = 55555
    //서버 객체를 생성
    val serverSocket = ServerSocket(portNumber)
    //사용자 접속 대기
    println("사용자 접속 대기")
    val socket = serverSocket.accept()
    println(socket)

    //클라이언트와 연결된 스트림 추출
    val inputStream = socket.getInputStream()
    val dataInputStream = DataInputStream(inputStream)

    val outputStream = socket.getOutputStream()
    val dataOutputStream = DataOutputStream(outputStream)

    //클라이언트에게 데이터 전달
    dataOutputStream.writeInt(100)
    dataOutputStream.writeDouble(11.11)
    dataOutputStream.writeBoolean(true)
    dataOutputStream.writeUTF("서버가 보낸 문자열")

    //클라이언트로부터 데이터 받기
    val data1 = dataInputStream.readInt()
    val data2 = dataInputStream.readDouble()
    val data3 = dataInputStream.readBoolean()
    val data4 = dataInputStream.readUTF()

    println("data1 : $data1")
    println("data2 : $data2")
    println("data3 : $data3")
    println("data4 : $data4")

    socket.close()
    serverSocket.close()
}
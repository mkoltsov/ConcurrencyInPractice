ServerSocket socket = new ServerSocket(80)
while (true) {
    Socket connection = socket.accept()
    println(connection)
}
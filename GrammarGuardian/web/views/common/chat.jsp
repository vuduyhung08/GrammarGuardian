
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <head>
        <link
            href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css"
            rel="stylesheet"
            integrity="sha384-QWTKZyjpPEjISv5WaRU9OFeRpok6YctnYmDr5pNlyT2bRjXh0JMhjY6hW+ALEwIH"
            crossorigin="anonymous"
            />
        <title>Grammar Guradian</title>
    </head>
    <body>	
        <jsp:include page="header.jsp"/>
        <div class="container">
            <div class="row" style="align-items: center; justify-content: center; margin-top: 150px">
                <h2>Contact with admin</h2>
                <textarea id="textAreaMessage" rows="10" cols="50"></textarea>
                <input style="margin-top: 30px" placeholder="Your message" id="textMessage" type="text" />
                <input onclick="sendMessage()" value="Send Message" class="btn btn-success" type="button" /> <br/><br/>
            </div>
        </div>
        <jsp:include page="footer.jsp"/>
        <script type="text/javascript">
            var websocket = new WebSocket("ws://localhost:9999/GrammarGuardian/chathub");
            websocket.onopen = function (message) {
                processOpen(message);
            };
            websocket.onmessage = function (message) {
                processMessage(message);
            };
            websocket.onclose = function (message) {
                processClose(message);
            };
            websocket.onerror = function (message) {
                processError(message);
            };

            function processOpen(message) {
                textAreaMessage.value += "Server connect... \n";
            }
            function processMessage(message) {
                console.log(message);
                textAreaMessage.value += message.data + " \n";
            }
            function processClose(message) {
                textAreaMessage.value += "Server Disconnect... \n";
            }
            function processError(message) {
                textAreaMessage.value += "Error... " + message + " \n";
            }

            function sendMessage() {
                if (typeof websocket != 'undefined' && websocket.readyState == WebSocket.OPEN) {
                    websocket.send(textMessage.value);
                    textMessage.value = "";
                }
            }
            
            function startChat() {
                 if (typeof websocket != 'undefined' && websocket.readyState == WebSocket.OPEN) {
                    websocket.send(textMessage.value);
                    textMessage.value = "${USER.firstName} ${USER.lastName}";
                }
            }

        </script>
    </body>
</html>
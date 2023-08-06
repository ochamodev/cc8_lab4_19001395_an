package CCVIII.Lab04;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

import java.util.logging.FileHandler;
import java.util.logging.ConsoleHandler;
import java.util.logging.Handler;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static java.lang.System.err;
import static java.lang.System.out;

public class ClientHandler implements Runnable {
    private Socket clientSocket;
    private static Logger LOGGER;

    private String name;

    public void setName(String name) {
        this.name = name;
    }

    public ClientHandler(Socket clientSocket, Logger logger) {
        this.clientSocket = clientSocket;
        LOGGER = logger;
    }

    @Override
    public void run() {
        try {
            BufferedReader reader = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
            PrintWriter writer = new PrintWriter(clientSocket.getOutputStream(), true);
            String requestLine;
            StringBuilder requestBuilder = new StringBuilder();
            writer.format("%s 220 %s Service Ready %s", Constants.ANSI_YELLOW, Constants.SERVER_DOMAIN, Constants.CRLR);
            while ((requestLine = reader.readLine()) != null && !requestLine.isEmpty()) {
            	requestBuilder.append(requestLine).append("\n");
                Pattern heloPattern = Pattern.compile(Commands.HELO_COMMAND);
                Matcher heloMatcher = heloPattern.matcher(requestLine);
                if (heloMatcher.matches()) {
                    String domain = heloMatcher.group(1);
                    if (domain.equals(Constants.SERVER_DOMAIN)) {
                        writer.format("%s Hello %domain, I am glad to meet you %s", StatusCodes.STATUS_250, domain, Constants.CRLR);
                    } else {
                        writer.format("%s Domain mismatch. Your HELO domain does not match the server's domain. %s", StatusCodes.STATUS_550, Constants.CRLR);
                    }
                } 
            }
            LOGGER.log(Level.INFO, "[Request]\n\r" + requestBuilder.toString().trim());  

            reader.close();
            writer.close();
            clientSocket.close();
        } catch (IOException e) {
        	LOGGER.log(Level.WARNING, "Error: " + e.getMessage() );
            e.printStackTrace();
        }
    }
}

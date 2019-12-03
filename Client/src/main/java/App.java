import akka.actor.ActorRef;
import akka.actor.ActorSystem;
import akka.actor.Props;
import java.util.Scanner;

public class App {
    public static void main(String args[]){
        boolean quitApp = false;
        String input;
        Scanner reader = new Scanner(System.in);
        ActorSystem userSystem = ActorSystem.create("UserWhatsApp");
        ActorRef userActor = userSystem.actorOf(Props.create(UserActor.class), "userActor");
        ActorRef parseActor = userSystem.actorOf(Props.create(ParserActor.class, userActor), "parserActor");


        System.out.println("Enter \"/user connect <username>\" to connect to the server");

        while(!quitApp){
            input = reader.nextLine();
            if (input.equals("quit")) quitApp = true;
            else { parseActor.tell(input, parseActor); }
        }
    }
}

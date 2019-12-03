import akka.actor.AbstractActor;
import akka.actor.ActorRef;
import akka.actor.Props;


public class ParserActor extends AbstractActor {
    private final ActorRef userActor;
    private String userName=null;

    static public Props props(ActorRef userRef){
        return Props.create(ParserActor.class, () -> new ParserActor(userRef));
    }

    public ParserActor(ActorRef userRef){
        this.userActor = userRef;
    }

    private void setUserName(String connectCmd) {
        //check if input is legal
        if (isConnectMsg(connectCmd)) {
            this.userName = connectCmd.split(" ")[2];
        }
    }

    private boolean isConnectMsg(String msg){
        if(msg==null) return false;
        String [] splitMsg = msg.split(" ");
        if (splitMsg[0]=="/user" && splitMsg[1] =="connect" && splitMsg.length==3)
            return true;
        return false;
    }

    /*private void parseCommand(String msg){
        String [] input = msg.split(" ");
        if(isValid(input)){
            if(input[0]=="/user"){
                switch (input[1]){
                    case "text":

                    case "file":
                    case "connect":
                    case "disconnect":


                }

            }
            else{ //group commands


            }



        }



    }*/

    private boolean isValid(String[] command){
        if(command[0]!= "/user" && command[0]!= "/group")
            return false;
        if(command.length> 5)
            return false;
        return true;
    }

    @Override
    public Receive createReceive() {
         return receiveBuilder()
                 .match(String.class, (msg) ->System.out.println("im parser "+msg) )
                 .matchAny((cmd) -> System.out.println("Invalid Command")).build();

    }
}

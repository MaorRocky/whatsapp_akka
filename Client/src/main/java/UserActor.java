import akka.actor.AbstractActor;
import akka.actor.ActorRef;
import akka.actor.ActorSelection;
import akka.actor.Props;

public class UserActor extends AbstractActor{
    private ActorSelection serverRef;
  /*  static public Props props(){
        return Props.create(UserActor.class);
    }*/

    public UserActor(){
    }

    public void preStart(){
        serverRef = getContext().actorSelection(
                "akka.tcp://ServerSystem@127.0.0.1:3553/user/Server");
    }

    @Override
    public Receive createReceive() {
        return receiveBuilder().matchAny((cmd) -> System.out.println(cmd)).build();
    }
}

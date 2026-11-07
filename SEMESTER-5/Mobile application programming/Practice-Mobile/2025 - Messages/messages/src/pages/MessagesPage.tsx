import React from "react";
import Message from "../datas/Message";
import {IonContent, IonPage} from "@ionic/react";
import MessagesList from "../components/MessagesList";

const MessagesPage: React.FC<{ messages: Message[], setMessages: (messages: Message[]) => void }> = ({
                                                                                                         messages,
                                                                                                         setMessages
                                                                                                     }) => {
    return (
        <IonPage>
            <IonContent>
                <MessagesList messages={messages} setMessages={setMessages}/>
            </IonContent>
        </IonPage>
    )
}

export default MessagesPage;
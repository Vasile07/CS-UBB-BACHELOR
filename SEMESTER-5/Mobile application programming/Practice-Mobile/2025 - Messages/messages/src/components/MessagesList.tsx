import React, {useEffect, useState} from "react";
import {IonIcon, IonInfiniteScroll, IonItem, IonLabel, IonList} from "@ionic/react";
import Message from "../datas/Message";
import axios from "axios";
import {eye, eyeOff} from "ionicons/icons"

const MessagesList: React.FC<{messages: Message[], setMessages: (messages: Message[]) => void}> = ({messages, setMessages}) => {

    const sortList = (list: Message[]) => {
        return list.sort((message1: Message, message2: Message) => {
            if(message1.read && !message2.read)
                return -1
            if(!message1.read && message2.read)
                return 1
            return -1*(message1.created - message2.created)
        })
    }

    return (
        <IonList>
            {
                sortList(messages).map((message) => (
                    <IonItem key={message.id}>
                        <IonIcon icon={message.read ? eye : eyeOff}/>
                        <IonLabel>{message.text}({message.created})</IonLabel>
                    </IonItem>
                ))
            }
        </IonList>
    )
}

export default MessagesList;
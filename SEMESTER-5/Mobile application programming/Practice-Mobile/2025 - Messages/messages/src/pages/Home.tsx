import {
    IonContent,
    IonHeader, IonIcon,
    IonPage,
    IonTab,
    IonTabBar,
    IonTabButton,
    IonTabs,
    IonTitle,
    IonToolbar
} from '@ionic/react';
import ExploreContainer from '../components/ExploreContainer';
import './Home.css';
import MessagesList from "../components/MessagesList";
import React, {useEffect, useState} from "react";
import Message from "../datas/Message";
import axios from "axios";
import UserList from "../components/UserList";
import MessagesPage from "./MessagesPage";
import UserPage from "./UserPage";
import {chatboxEllipses, person} from "ionicons/icons";

const Home: React.FC = () => {
    const [messages, setMessages] = useState<Message[]>([])

    useEffect(() => {
        axios.get("http://localhost:3000/message")
            .then((result) => {
                setMessages(result.data);
            })
            .catch((error) => {
                console.log("Error fetching messages: ", error)
            })
    }, []);

    useEffect(() => {
        localStorage.setItem("messages", JSON.stringify(messages))
    }, [messages]);

    return (
       <IonTabs>
           <IonTab tab="messages">
               <MessagesPage  messages={messages} setMessages={setMessages}/>
           </IonTab>

           <IonTab tab="users">
               <UserPage  messages={messages} setMessages={setMessages}/>
           </IonTab>

           <IonTabBar slot="bottom">
               <IonTabButton tab="messages">
                   <IonIcon icon={chatboxEllipses} />
                   Messages
               </IonTabButton>
               <IonTabButton tab="users">
                   <IonIcon icon={person} />
                   Users
               </IonTabButton>
           </IonTabBar>
       </IonTabs>
    );
};

export default Home;

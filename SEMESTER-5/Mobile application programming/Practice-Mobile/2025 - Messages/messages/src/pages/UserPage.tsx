import React, { useEffect, useRef, useState } from "react";
import Message from "../datas/Message";
import { IonContent, IonItem, IonList, IonPage } from "@ionic/react";
import UserList from "../components/UserList";
import { createAnimation } from "@ionic/react";
import {updateReadState} from "../utils/UtilsFunctions";

const UserPage: React.FC<{ messages: Message[]; setMessages: (messages: Message[]) => void }> = ({ messages, setMessages }) => {
    const [selectedUser, setSelectedUser] = useState<string | null>(null);
    const [visibleMessages, setVisibleMessages] = useState<Record<string, boolean>>({}); // Tracks visibility

    const filterMessages = () => {
        return messages
            .filter((message) => message.sender === selectedUser)
            .sort((m1, m2) => m1.created - m2.created);
    };

    const handleIntersection = (entries: IntersectionObserverEntry[]) => {
        entries.forEach((entry) => {
            if (entry.isIntersecting) {
                const id = entry.target.getAttribute("data-id");

                if (id) {
                    const message = messages.find((m) => m.id === parseInt(id));
                    if(message && !message.read) {
                        const animation = createAnimation()
                            .addElement(entry.target)
                            .duration(1000)
                            .fromTo("fontWeight", "bold", "normal")
                        animation.play();

                        updateReadState(message);
                        setMessages(messages.map((m) => {
                            if( m.id != message.id)
                                return m;
                            return {
                                ...m,
                                read: !message.read
                            }
                        }))
                    }
                }
            }
        });
    };

    useEffect(() => {
        const observer = new IntersectionObserver(handleIntersection, {
            threshold: 0.1,
        });

        const items = document.querySelectorAll(".animated-item");
        items.forEach((item) => observer.observe(item));

        return () => observer.disconnect();
    }, [messages, selectedUser]);

    return (
        <IonPage>
            <IonContent>
                <UserList messages={messages} setSelectedUser={setSelectedUser} />
            </IonContent>

            <IonContent>
                <IonList>
                    {filterMessages().map((message) => (
                        <IonItem
                            key={message.id}
                            className="animated-item"
                            data-id={message.id}
                        >
                            {message.text}
                        </IonItem>
                    ))}
                </IonList>
            </IonContent>
        </IonPage>
    );
};

export default UserPage;

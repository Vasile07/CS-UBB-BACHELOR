import {IonIcon, IonItem, IonList} from "@ionic/react";
import Message from "../datas/Message";
import React, {useEffect, useState} from "react";

interface UserProps {
    name: string,
    numberOfUnreadenMessages: number
}


const UserList: React.FC<{ messages: Message[], setSelectedUser: (user: string) => void }> = ({
                                                                                                     messages,
                                                                                                  setSelectedUser
                                                                                                 }) => {
    const [users, setUsers] = useState<UserProps[]>([])

    useEffect(() => {
        const uniqueNames: string[] = []


        messages.map((message) => message.sender).forEach((userName) => {
            if (!uniqueNames.includes(userName))
                uniqueNames.push(userName)
        })


        setUsers(uniqueNames.map((name) => (
            {
                name: name,
                numberOfUnreadenMessages: messages.filter((m) => m.sender === name).length
            }
        )))

    }, [messages]);

    return (
        <IonList>
            {
                users.map((user) => (
                    <IonItem key={user.name} onClick={() => setSelectedUser(user.name)}>{user.name} [{user.numberOfUnreadenMessages}]</IonItem>
                ))
            }
        </IonList>
    )
}

export default UserList;
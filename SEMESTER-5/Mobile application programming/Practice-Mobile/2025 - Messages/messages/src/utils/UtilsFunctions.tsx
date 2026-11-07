import axios from "axios";
import Message from "../datas/Message";

const updateReadState = (message : Message) => {
    axios.put(`http://localhost:3000/message/${message.id}`, {...message, read: !message.read})
        .then((response) => {

        })
        .catch((error) => {
            updateReadState(message)
        })
}

export {updateReadState};
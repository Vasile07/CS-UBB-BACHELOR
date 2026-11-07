import CloudyBackground from "../components/CloudyBackground";
import LexiDog from "../components/LexiDog";
import {useNavigate} from 'react-router-dom';
import {useEffect, useState} from "react";

export default function StartPage() {
    const navigate = useNavigate();  // Use the useNavigate hook
    const [text, setText] = useState('Bine ai venit în LexiLearn! Eu sunt Lexi și împreună învățăm și ne distrăm! Pentru a continua apasă butonul verde.');

    useEffect(() => {
        if (text) {
            window.speechSynthesis.cancel();

            const speech = new SpeechSynthesisUtterance(text);
            speech.lang = 'ro-RO';
            speech.pitch = 0.5;
            speech.rate = 0.8;
            window.speechSynthesis.speak(speech);
        }
    }, []);

    const goToLevels = () => {
      navigate('/levels');  // Programmatically navigate to LevelsPage
    };
    return (
        <div style={styles.background}>
            <CloudyBackground />
            <div style={styles.titleContainer}>
                <p style={styles.title}>
                    LexiLearn
                </p>
                <div style={styles.button} onClick={goToLevels}>
                    <p style={styles.buttonText}>
                        START
                    </p>
                </div>
            </div>
            <LexiDog text={text}/>
        </div>
    )
}

const styles = {
    background: {
        backgroundColor: "#FFF",
        width: "100%",
        height: "100%",
        position: "relative",
        display: "flex",
        justifyContent: "center",
        alignItems: "center",
    },
    titleContainer: {
        position: "absolute",
        top: 50,
        display: "flex",
        flexDirection: "column",
        justifyContent: "center",
        alignItems: "center",
    },
    title: {
        color: "#94DD2F",
        fontFamily: "Jomhuria, sans-serif",
        fontSize: 200,
        margin: 0,
        textShadow: "0 0 5px black, 0 0 5px black, 0 0 5px black, 0 0 5px black, 0 0 5px black, 0 0 5px black, 0 0 5px black, 0 0 5px black, 0 0 5px black"
    },
    button: {
        width: "80%",
        height: 80,
        backgroundColor: "#94DD2F",
        display: "flex",
        justifyContent: "center",
        alignItems: "flex-end",
        border: "3px solid #000",
        borderRadius: 25,
    },
    buttonText: {
        color: "#000",
        fontFamily: "Jomhuria, sans-serif",
        fontSize: 70,
        margin: 0,
    }
};
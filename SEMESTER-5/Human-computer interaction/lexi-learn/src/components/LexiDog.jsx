import dog from "../assets/images/dog.png";
import {useCallback, useEffect, useState} from "react";

export default function LexiDog({text}) {
    console.log(`Lext ${text}`);
    const [enunt, setEnunt] = useState('');

    const speakText = useCallback((text) => {
        window.speechSynthesis.cancel();

        const speech = new SpeechSynthesisUtterance(text);

        speech.lang = 'ro-Ro';
        speech.pitch = 1;
        speech.rate = 1;
        window.speechSynthesis.speak(speech);
    }, []);

    useEffect(() => {
        setEnunt(text);
    }, []);

    useEffect(() => {
        console.log(text)
        speakText(text);

    }, [text]);

    return (
        <div style={styles.container} onClick={() => speakText(enunt)}>
            <img src={dog} width={170} height={170} alt="Lexi"/>
        </div>
    );
}

const styles = {
    container: {
        cursor: 'pointer',
        position: "absolute",
        bottom: 0,
        left: 0,
    }
};

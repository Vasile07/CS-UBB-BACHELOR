import LexiHole from "../LexiHole";
import {useCallback, useEffect, useState} from "react";
import PropTypes from "prop-types";

export default function PrindeLexiJoc({grupuri, completeLevel, setText}) {
    const [currentIndexUp, setCurrentIndexUp] = useState(null);
    const [currentText, setCurrentText] = useState(grupuri[0]);
    const [clicked, setClicked] = useState(grupuri.map(g => false))

    useEffect(() => {
        console.log(clicked)
        if (clicked.filter(c => !c).length === 0)
            completeLevel()
    }, [clicked, completeLevel]);


    const clickGrup = (gr) => {
        setClicked(clicked.map((c, index) => grupuri[index] === gr ? true : c))
    }

    const isLexiIndexUp = useCallback((index) => {
        return index === currentIndexUp;
    }, [currentIndexUp]);

    const getRandomIndex = (exclude) => {
        let randomIndex;
        do {
            randomIndex = Math.floor(Math.random() * 6) + 1;
        } while (randomIndex === exclude);
        return randomIndex;
    };

    function getRandomText(exclude) {
        let randomText;
        do {
            randomText = grupuri[Math.floor(Math.random() * grupuri.length)];
        } while (randomText === exclude);
        return randomText;
    }

    const speakText = (text) => {
        // const speech = new SpeechSynthesisUtterance(text);
        // speech.lang = "ro-RO";
        // speech.pitch = -2;
        // speech.rate = 1;
        // window.speechSynthesis.speak(speech);
        setText(text)
    };

    const onLexiClick = (index) => {
        if (index === currentIndexUp) {
            speakText(currentText)
            clickGrup(currentText)

            const timerIndex = setTimeout(() => {
                const newIndex = getRandomIndex(index);
                setCurrentIndexUp(newIndex);
            }, 200);

            const timerText = setTimeout(() => {
                const newText = getRandomText(null); // Initial random index
                setCurrentText(newText)
            }, 700);
        }
    };

    useEffect(() => {

        const timer = setTimeout(() => {
            const newIndex = getRandomIndex(null); // Initial random index
            setCurrentIndexUp(newIndex);
        }, 1200);

        return () => clearTimeout(timer); // Cleanup on unmount
    }, []);

    return (
        <div style={styles.pageContainer}>
            <div style={styles.row}>
                <div style={{ width: "15%", height: "100%" }}>
                    <LexiHole isUp={isLexiIndexUp(1)} onClick={() => onLexiClick(1)} text={currentText} factor={1}/>
                </div>
                <div style={{ width: "15%", height: "100%" }}>
                    <LexiHole isUp={isLexiIndexUp(2)} onClick={() => onLexiClick(2)} text={currentText} factor={1}/>
                </div>
                <div style={{ width: "15%", height: "100%" }}>
                    <LexiHole isUp={isLexiIndexUp(3)} onClick={() => onLexiClick(3)} text={currentText} factor={1}/>
                </div>
            </div>
            <div style={styles.row}>
                <div style={{ width: "15%", height: "100%" }}>
                    <LexiHole isUp={isLexiIndexUp(4)} onClick={() => onLexiClick(4)} text={currentText} factor={2}/>
                </div>
                <div style={{ width: "15%", height: "100%" }}>
                    <LexiHole isUp={isLexiIndexUp(5)} onClick={() => onLexiClick(5)} text={currentText} factor={2}/>
                </div>
                <div style={{ width: "15%", height: "100%" }}>
                    <LexiHole isUp={isLexiIndexUp(6)} onClick={() => onLexiClick(6)} text={currentText} factor={2}/>
                </div>
            </div>
        </div>
    );
}

const styles = {
    pageContainer: {
        marginTop: "15%",
        width: "100%",
        display: "flex",
        flex: 1,
        gap: "10vh",
        flexDirection: "column",
        alignItems: "center",
        justifyContent: "center",
    },
    row: {
        width: "80%",
        height: "50%",
        display: "flex",
        flexDirection: "row",
        alignItems: "flex-start",
        justifyContent: "space-around",
    },
};

PrindeLexiJoc.propTypes = {
    grupuri: PropTypes.arrayOf(PropTypes.string).isRequired,
};

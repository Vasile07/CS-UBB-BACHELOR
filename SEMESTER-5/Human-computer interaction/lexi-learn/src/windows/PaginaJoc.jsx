import LexiDog from "../components/LexiDog";
import button from "../assets/images/buttons/SmallButton.png"
import homeIcon from "../assets/images/icons/HomeIcon.png"
import nextIcon from "../assets/images/icons/PlayIcon.png"
import React, {useState} from "react";
import {useNavigate} from "react-router-dom";
import lock from "../assets/images/icons/lock.png";

export default function PaginaJoc({component, title, enunt, levelLink, nextLevelLink, params, levelNumber}) {
    const [text, setText] = useState(enunt);
    const handleSetText = (newText) => {
        if (text === newText) {
            setText("");
            setTimeout(() => setText(newText), 0);
        } else {
            setText(newText);
        }
    };
    const navigate = useNavigate();  // Use the useNavigate hook

    const [isCompleted, setIsCompleted] = useState(false);
    const completeLevel = () => {
        if (isCompleted) {
            const currentLevel = parseInt(localStorage.getItem("currentLevel"))
            const nextLevel = levelNumber + 1
            localStorage.setItem("currentLevel", nextLevel > currentLevel ? nextLevel.toString() : currentLevel.toString())
            navigate('/level-complete', {state: {levelLink: levelLink, nextLevel: nextLevelLink}});
        } else
            handleSetText("Încă nu ai completat tot nivelul. Ajută-l pe Lexi până la capăt.")
    }

    return (
        <div style={styles.pageContainer}>
            <div style={styles.header}>
                <div style={styles.homeButton} onClick={() => {
                    navigate('/levels');
                }}>
                    <img
                        src={button}
                        style={{width: "100%", height: "100%", objectFit: "contain", position: "absolute"}}
                        alt={"button"}
                    />
                    <img
                        src={homeIcon}
                        style={{
                            width: "60%", height: "60%", objectFit: "contain", position: "absolute", top: "50%",
                            transform: "translateY(-65%)",
                        }}
                        alt={"home"}
                    />
                </div>
                <div style={styles.titleBanner}>
                    {title}
                </div>
                <div style={styles.nextButton} onClick={() => {
                    completeLevel()
                }}>
                    <img
                        src={button}
                        style={{width: "100%", height: "100%", objectFit: "contain", position: "absolute"}}
                        alt={"button"}
                    />
                    <img
                        src={nextIcon}
                        style={{
                            width: "60%", height: "60%", objectFit: "contain", position: "absolute", top: "50%",
                            transform: "translateY(-65%)",
                        }}
                        alt={"home"}
                    />
                    {
                        !isCompleted &&
                        <div style={styles.lockdiv}>
                            <img src={lock} width={"auto"} height={"40%"} alt={"lock"}/>
                        </div>
                    }
                </div>
            </div>
            <div style={styles.gameScreen}>
                {component && React.createElement(component, {
                    ...params,
                    setText: handleSetText,
                    completeLevel: () => setIsCompleted(true)
                })}
            </div>
            <div>
                <LexiDog text={text}/>
            </div>
        </div>
    )
}

const styles = {
    pageContainer: {
        width: "100%",
        height: "100%",
        backgroundColor: "#F5E3A9",
    },
    header: {
        width: "100%",
        height: "15%",
        display: "flex",
        flexDirection: "row",
        alignItems: "center",
        justifyContent: "center",
    },
    homeButton: {
        display: "flex",
        alignItems: "center",
        justifyContent: "center",
        width: "20%",
        height: "90%",
        position: "relative",
        cursor: "pointer"
    },
    titleBanner: {
        width: "60%",
        height: "100%",
        display: "flex",
        alignItems: "center",
        justifyContent: "center",
        backgroundColor: "#94DD2F",
        color: "#fff",
        textShadow: "0 0 2px black, 0 0 2px black, 0 0 2px black, 0 0 2px black, 0 0 2px black, 0 0 2px black, 0 0 2px black, 0 0 2px black, 0 0 2px black",
        fontSize: "6vw",
        fontFamily: "Jomhuria, sans-serif",
    },
    nextButton: {
        display: "flex",
        alignItems: "center",
        justifyContent: "center",
        width: "20%",
        height: "90%",
        position: "relative",
        cursor: "pointer"
    },
    gameScreen: {
        width: "100%",
        height: "85%",
        display: "flex",
    },
    lockdiv: {
        height: "100%",
        aspectRatio: 1,
        display: "flex",
        justifyContent: "center",
        alignItems: "center",
        backgroundColor: '#D9D9D9',
        opacity: 0.9,
        border: "3px black solid",
        borderRadius: "30%"
    },
}
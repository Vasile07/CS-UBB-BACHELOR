import CloudyBackground from "../components/CloudyBackground";
import LexiDog from "../components/LexiDog";
import React from "react";
import levelsBackground from "../assets/images/menu/Window.png";
import ribbon from "../assets/images/menu/Ribbon.png";
import button from "../assets/images/buttons/SmallButton.png";
import orangeButton from "../assets/images/buttons/OrangeButton.png";
import homeIcon from "../assets/images/icons/HomeIcon.png";
import replayIcon from "../assets/images/icons/ReplayIcon.png"
import nextIcon from "../assets/images/icons/PlayIcon.png"
import {useLocation, useNavigate} from "react-router-dom";

export default function LevelComplete() {
    const navigate = useNavigate();
    const location = useLocation();
    const levelLink = location.state?.levelLink;
    const nextLevel = location.state?.nextLevel;

    return (
        <div style={styles.pageContainer}>
            <style>
                {`
                @keyframes flicker {
                  0%, 100% {
                    filter: drop-shadow(0 0 5px rgba(255, 255, 255, 0.5)) drop-shadow(0 0 10px rgba(255, 255, 255, 0.3));
                  }
                  50% {
                    filter: drop-shadow(0 0 20px rgba(255, 255, 255, 1)) drop-shadow(0 0 25px rgba(255, 255, 255, 0.8));
                  }
                }
              `}
            </style>
            <CloudyBackground/>
            <LexiDog
                text={"Felicitări! Ai reușit să rezolvi perfect acest nivel! Pentru a continua apasă butonul verde. Dacă dorești să refaci nivelul apasă butonul portocaliu."}/>
            <div style={styles.levelsContainer}>
                <img src={levelsBackground} width={"100%"} height={"100%"} alt="Background"/>
                <div style={styles.ribbonContainer}>
                    <img src={ribbon} style={styles.ribbon} alt="Ribbon"/>
                    <p style={styles.ribbonText}>Felicitări!</p>
                </div>
                <div style={styles.buttonContainer}>
                    <div style={{...styles.button}} onClick={() => {
                        if (nextLevel)
                            navigate(`/levels/${nextLevel}`);
                        else
                            navigate(`/levels`);
                    }}>
                        <img
                            src={button}
                            style={{
                                width: "100%", height: "100%", objectFit: "contain", gridColumn: 1,
                                gridRow: 1,
                                animation: "flicker 1s ease-in infinite"
                            }}
                            alt={"button"}
                        />
                        <img
                            src={nextIcon}
                            style={{
                                width: "60%", height: "60%", objectFit: "contain", gridColumn: 1,
                                gridRow: 1,
                                transform: "translateY(-10%)"
                            }}
                            alt={"home"}
                        />
                    </div>
                    <div style={styles.orangeButton} onClick={() => {
                        navigate(`/levels/${levelLink}`);
                    }}>
                        <img
                            src={orangeButton}
                            style={{
                                width: "100%", height: "100%", objectFit: "contain", gridColumn: 1,
                                gridRow: 1
                            }}
                            alt={"button"}
                        />
                        <img
                            src={replayIcon}
                            style={{
                                width: "60%", height: "60%", objectFit: "contain", gridColumn: 1,
                                gridRow: 1,
                                transform: "translateY(-10%)"
                            }}
                            alt={"home"}
                        />
                    </div>
                    <div style={styles.button} onClick={() => {
                        navigate('/levels');
                    }}>
                        <img
                            src={button}
                            style={{
                                width: "100%", height: "100%", objectFit: "contain", gridColumn: 1,
                                gridRow: 1
                            }}
                            alt={"button"}
                        />
                        <img
                            src={homeIcon}
                            style={{
                                width: "60%", height: "60%", objectFit: "contain", gridColumn: 1,
                                gridRow: 1,
                                transform: "translateY(-10%)"
                            }}
                            alt={"home"}
                        />
                    </div>
                </div>
            </div>

        </div>
    )
}

const styles = {
    pageContainer: {
        background: "#66C1DA",
        width: "100%",
        height: "100%",
        position: "relative",
        display: "flex",
        flexDirection: "column",
        alignItems: "center",
        justifyContent: "center",
    },
    levelsContainer: {
        width: "25%",
        height: "75%",
        position: "absolute",
    },
    ribbonContainer: {
        position: "absolute",
        top: -20,
        left: "50%",
        transform: "translateX(-50%)",
        width: "100%",
        display: "flex",
        flexDirection: "column",
        alignItems: "center",
        justifyContent: "center",
        zIndex: 1,
    },
    ribbon: {
        width: "70%",
        height: "auto",
    },
    ribbonText: {
        margin: 0,
        fontSize: "2.5rem",
        fontWeight: "bold",
        color: "#fff",
        textAlign: "center",
        position: "absolute",
        top: "40%",
        transform: "translateY(-50%)",
    },
    buttonContainer: {
        position: "absolute",
        top: "50%",
        height: "auto",
        transform: "translateY(-50%)",
        display: "flex",
        flexDirection: "column",
        justifyContent: "center",
        alignItems: "center",
        width: "100%",
        gap: 20,
    },
    button: {
        display: "grid",
        alignItems: "center",
        justifyItems: "center",
        width: "30%",
        height: "auto"
    },
    orangeButton: {
        display: "grid",
        alignItems: "center",
        justifyItems: "center",
        width: "20%",
        height: "auto"
    }
}
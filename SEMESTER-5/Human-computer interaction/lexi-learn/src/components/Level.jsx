import buttonBackground from "../assets/images/buttons/ButtonBackground.png"
import button from "../assets/images/buttons/SmallButton.png"
import {useNavigate} from "react-router-dom";
import lock from "../assets/images/icons/lock.png"
import {useEffect, useState} from "react";

export default function Level({level, path, setText}) {
    const navigate = useNavigate();
    const [isLocked, setIsLocked] = useState(true);
    const goToLevel = () => {
        if (!isLocked)
            navigate(path);
        else
            setText("Nivelul este încă blocat.")
    }
    useEffect(() => {
        if (level <= parseInt(localStorage.getItem("currentLevel")))
            setIsLocked(false)
        else
            setIsLocked(true)
    }, [level, localStorage.getItem("currentLevel")]);
    
    return (
        <div style={styles.levelContainer} onClick={goToLevel}>
            <img src={buttonBackground} style={styles.buttonBackground} alt="buttonBackground"></img>
            <img src={button} style={styles.button} alt="button"></img>
            <p style={styles.text}>{level}</p>
            {
                isLocked &&
                <div style={styles.lockdiv}>
                    <img src={lock} width={"auto"} height={"40%"} alt={"lock"}/>
                </div>
            }

        </div>
    )
}

const styles = {
    levelContainer: {
        width: "100%",
        height: "100%",
        position: "relative",
        display: "flex",
        flexDirection: "column",
        justifyContent: "center",
        alignItems: "center",
        cursor: "pointer"
    },
    buttonBackground: {
        position: "absolute",
        top: 0,
        width: "100%",
        height: "100%",
    },
    button: {
        position: "absolute",
        top: "8%",
        width: "auto",
        height: "46%",
    },
    lockdiv: {
        position: "absolute",
        top: "5%",
        height: "46%",
        aspectRatio: 1,
        display: "flex",
        justifyContent: "center",
        alignItems: "center",
        backgroundColor: '#D9D9D9',
        opacity: 0.9,
        border: "3px black solid",
        borderRadius: "30%"
    },
    text: {
        color: "#fff",
        textShadow: "0 0 3px black, 0 0 3px black, 0 0 3px black, 0 0 3px black, 0 0 3px black, 0 0 3px black, 0 0 3px black, 0 0 3px black, 0 0 3px black",
        margin: 0,
        position: "absolute",
        top: "10%",
        fontSize: 50,
        fontFamily: "Jomhuria, sans-serif",
    }
}
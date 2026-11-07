import React, {useState} from "react"
import LexiDog from "../components/LexiDog"
import Levels from "../components/Levels"
import CloudyBackgorund from "../components/CloudyBackground"

export default function LevelsPage(properties){
    const [text, setText] = useState("Alege un nivel pe care doreşti să îl parcurgi.");
    const handleSetText = (newText) => {
        if (text === newText) {
            setText("");
            setTimeout(() => setText(newText), 0);
        } else {
            setText(newText);
        }
    };
    return (
        <div style={styles.pageContainer}>    
            <CloudyBackgorund />
            <LexiDog text={text}/>
            <div style={styles.levelsContainer}>
                <Levels setText={handleSetText}/>
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
        overflow: "hidden",
    },
    levelsContainer: {
        width: "40%",
        height: "75%",
        position: "absolute",
    }
}
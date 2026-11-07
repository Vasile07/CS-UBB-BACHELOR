import levelsBackground from "../assets/images/menu/Window.png";
import ribbon from "../assets/images/menu/Ribbon.png";
import Level from "./Level";
import {useLevels} from "../LevelsProvider";

export default function Levels({setText}) {
    const levels = useLevels();

    const getLevelContainer = (levelNumber, levelPath) => {
        return (
            <div style={{width: "15%", height: "20%"}}>
                <Level level={levelNumber} path={levelPath} setText={setText}/>
            </div>
        )
    }

    return (
        <div style={styles.pageContainer}>
            <img src={levelsBackground} width={"100%"} height={"75%"} alt="Background" />
            <div style={styles.ribbonContainer}>
                <img src={ribbon} style={styles.ribbon} alt="Ribbon" />
                <p style={styles.ribbonText}>Niveluri</p>
            </div>
            <div style={styles.levels}>
                {
                    levels.map(level => getLevelContainer(level.number, level.path))
                }
            </div>
        </div>
    );
}

const styles = {
    pageContainer: {
        width: "100%",
        height: "100vh", // Ensure the page takes up the full viewport height
        backgroundColor: "transparent", // Corrected typo from 'backgorundColor'
        position: "relative",
    },
    ribbonContainer: {
        position: "absolute", 
        top: -20, 
        left: "50%", 
        transform: "translateX(-50%)", 
        width: "80%", 
        display: "flex",
        flexDirection: "column",
        alignItems: "center",
        justifyContent: "center",
        zIndex: 1, 
    },
    ribbon: {
        width: "60%", // Make the ribbon take up the full width of the container
        height: "auto", // Keep the aspect ratio of the ribbon image
    },
    ribbonText: {
        margin: 0,
        fontSize: "2rem", 
        fontWeight: "bold",
        color: "#fff", 
        textAlign: "center",
        position: "absolute", 
        top: "50%", 
        transform: "translateY(-50%)", 
    },
    levels: {
        position: "absolute",
        top: "10%",
        left: "10%",
        width: "80%",
        height: "80%",
        display: "flex",
        flexDirection: "row",
        justifyContent: "center",
        alignItems: "center",
        alignContent: "flex-start",
        flexWrap: "wrap",
        gap: 20,
    }
};

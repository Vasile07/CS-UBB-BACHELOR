import {useEffect, useState} from "react";
import lac from "../../assets/images/games/GasesteDiferenta/lac.png"
import caine from "../../assets/images/games/GasesteDiferenta/caine.png"
import casa from "../../assets/images/games/GasesteDiferenta/casa.png"
import carte from "../../assets/images/games/GasesteDiferenta/carte.png"
import barca from "../../assets/images/games/GasesteDiferenta/barca.png"

const GasesteDiferentaJoc = ({completeLevel, setText}) => {

    // (lac) - mac
    // (caine) - paine
    // (casa) - masa
    // (carte) - parte
    // (barca) - parca

    const [cuvinte, setCuvinte] = useState([]);

    useEffect(() => {
        const perechi = [
            {cuv: ["lac", "mac"], optiuneCorecta: "lac", imagine: lac},
            {cuv: ["caine", "paine"], optiuneCorecta: "caine", imagine: caine},
            {cuv: ["casa", "masa"], optiuneCorecta: "casa", imagine: casa},
            {cuv: ["carte", "parte"], optiuneCorecta: "carte", imagine: carte},
            {cuv: ["barca", "parca"], optiuneCorecta: "barca", imagine: barca}
        ];

        const shuffleArray = (array) => {
            for (let i = array.length - 1; i > 0; i--) {
                const j = Math.floor(Math.random() * (i + 1));
                [array[i], array[j]] = [array[j], array[i]];
            }
        };

        shuffleArray(perechi);

        perechi.forEach(pereche => shuffleArray(pereche.cuv));

        setCuvinte(perechi);
    }, []);

    const [currentIndex, setCurrentIndex] = useState(0)
    const [isCorrect, setIsCorrect] = useState("")

    const verifyClick = (c) => {
        if (c === cuvinte[currentIndex].optiuneCorecta) {
            setIsCorrect(c)
            setText(`Felicitări! Ai ales corect cuvântul ${c}.`);
            setTimeout(() => {
                setIsCorrect("")
                if (currentIndex + 1 < cuvinte.length)
                    setCurrentIndex(currentIndex + 1)
                else {
                    completeLevel();
                }
            }, 3000)

        } else {
            setText(`Mai încearcă! Cuvântul ales a fost ${c}.`);
        }

    }

    return (
        cuvinte.length > 0 &&

        <div style={styles.pageContainer}>
            <div style={styles.imageRow}>
                <img style={styles.imageStyle} src={cuvinte[currentIndex].imagine}
                     alt={cuvinte[currentIndex].optiuneCorecta}/>
            </div>
            <div style={styles.optionsRow}>
                <div style={{
                    ...styles.optionButton,
                    backgroundColor: isCorrect === cuvinte[currentIndex].cuv[0] ? "#a5e24d" : "#fec230"
                }} key={cuvinte[currentIndex].cuv[0]}
                     onClick={() => verifyClick(cuvinte[currentIndex].cuv[0])}>
                    <p style={styles.optionText}>{cuvinte[currentIndex].cuv[0]}</p>
                </div>

                <p style={styles.sauText}>sau</p>

                <div style={{
                    ...styles.optionButton,
                    backgroundColor: isCorrect === cuvinte[currentIndex].cuv[1] ? "#a5e24d" : "#fec230"
                }} key={cuvinte[currentIndex].cuv[1]}
                     onClick={() => verifyClick(cuvinte[currentIndex].cuv[1])}>
                    <p style={styles.optionText}>{cuvinte[currentIndex].cuv[1]}</p>
                </div>
            </div>
        </div>

    )
}

export default GasesteDiferentaJoc

const styles = {
    pageContainer: {
        width: "100%",
        height: "100%",
        display: "flex",
        flexDirection: "column",
        justifyContent: "center",
        alignItems: "center",
    },
    imageRow: {
        width: "100%",
        height: "55%",
        display: "flex",
        flexDirection: "column",
        justifyContent: "center",
        alignItems: "center",
    },
    imageStyle: {
        height: "80%",
        width: "auto",
        maxWidth: "70%",
    },
    optionsRow: {
        width: "65%",
        display: "flex",
        flex: 1,
        flexDirection: "row",
        justifyContent: "space-between",
        alignItems: "center",
    },
    optionButton: {
        width: "fit-content",
        height: "fit-content",
        userSelect: "none",
        cursor: "pointer",
        padding: "5px 60px",
        borderRadius: "22%",
        borderTop: "0px solid white",    /* Top border */
        borderRight: "9px solid white", /* Right border */
        borderBottom: "7px solid white", /* Bottom border */
        borderLeft: "9px solid white"
    },
    optionText: {
        fontSize: "10vh",
        fontFamily: "Jomhuria, sans-serif",
        margin: 0,
        color: "#FFF",
    },
    sauText: {
        fontSize: "20vh",
        fontFamily: "Jomhuria, sans-serif",
        margin: 0,
        color: "#000"
    }
}
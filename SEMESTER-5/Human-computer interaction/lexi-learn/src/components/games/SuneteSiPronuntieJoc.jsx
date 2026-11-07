import grass from "../../assets/images/games/grass.png";
import pronuntie_a from "../../assets/images/games/Pronuntie/pronuntie_a.png"
import pronuntie_e from "../../assets/images/games/Pronuntie/pronuntie_e.png"
import pronuntie_i from "../../assets/images/games/Pronuntie/pronuntie_i.png"
import pronuntie_o from "../../assets/images/games/Pronuntie/pronuntie_o.png"
import pronuntie_u from "../../assets/images/games/Pronuntie/pronuntie_u.png"
import {useEffect, useState} from "react";

export default function SuneteSiPronuntieJoc({setText, completeLevel}) {
    const litere = [
        {litera: "a", pronuntie: "a", imagine: pronuntie_a},
        {litera: "e", pronuntie: "EEE", imagine: pronuntie_e},
        {litera: "i", pronuntie: "i", imagine: pronuntie_i},
        {litera: "o", pronuntie: "o", imagine: pronuntie_o},
        {litera: "u", pronuntie: "UUU", imagine: pronuntie_u}]

    const [clicked, setClicked] = useState(litere.map(l => false))

    const clickLitera = (litera, pronuntie) => {
        setText(pronuntie)

        setClicked(clicked.map((click, index) => litere[index].litera === litera ? true : click))
    }

    useEffect(() => {
        if (clicked.filter(click => !click).length === 0)
            completeLevel()
    }, [clicked, completeLevel]);

    return (
        <div style={styles.pageContainer}>
            <div style={styles.content}>
                {
                    litere.map(value => {
                        return (
                            <div style={styles.containerPronuntie}>
                                <p style={styles.letterSyle}>{value.litera}</p>
                                <div style={styles.circle} onClick={
                                    () => clickLitera(value.litera, value.pronuntie)
                                }>
                                    <div style={styles.triangle}></div>
                                </div>
                                <img src={value.imagine} alt={"Pronuntie A"}
                                     style={{width: "100%", height: "auto", marginTop: 20}}/>
                            </div>
                        )
                    })
                }
            </div>
            <img src={grass} style={styles.grass} alt="Grass background"/>
        </div>
    );
}

const styles = {
    pageContainer: {
        width: "100%",
        height: "100%",
        display: "flex",
        flexDirection: "column",
        alignItems: "center",
        justifyContent: "center",
    },
    content: {
        display: "flex",
        width: "100%",
        flex: 1,
        maxHeight: "73%",
        alignItems: "center",
        justifyContent: "center",
        gap: "5%",
        flexWrap: "wrap",
    },
    containerPronuntie: {
        display: "flex",
        flexDirection: "column",
        width: "15%",
        height: "90%",
        alignItems: "center",
        justifyContent: "center",
        textAlign: "center",
    },
    grass: {
        display: "flex",
        width: "100%",
        flex: 1,
        maxHeight: "27%",
    },
    letterSyle: {
        padding: 0,
        margin: 0,
        fontSize: 250,
        fontFamily: "Jomhuria, sans-serif",
        color: "#94DD2F",
        textShadow:
            "0 0 8px white, 0 0 8px white, 0 0 8px white, 0 0 8px white, 0 0 8px white, 0 0 8px white, 0 0 8px white, 0 0 8px white, 0 0 8px white",

    },
    circle: {
        width: "30%",
        aspectRatio: "1",
        borderRadius: "50%",
        display: "flex",
        alignItems: "center",
        justifyContent: "center",
        border: "5px solid #FFF",
        cursor: "pointer"
    },
    triangle: {
        width: "50%",
        height: "50%",
        backgroundColor: "#FFF", // Triangle color
        clipPath: "polygon(50% 0%, 100% 100%, 0% 100%)",
        transform: "rotate(90deg) translate(0%, -15%)",
    },

};

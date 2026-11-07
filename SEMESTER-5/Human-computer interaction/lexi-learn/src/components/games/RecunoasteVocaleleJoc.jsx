import React, {useEffect, useState} from "react";
import Cuvant from "../Cuvant";
import cal from "../../assets/images/games/RecunoasteVocale/cal.png";
import foc from "../../assets/images/games/RecunoasteVocale/foc.png";
import cer from "../../assets/images/games/RecunoasteVocale/cer.png";
import pix from "../../assets/images/games/RecunoasteVocale/pix.png";
import fulg from "../../assets/images/games/RecunoasteVocale/fulg.png";
import nas from "../../assets/images/games/RecunoasteVocale/nas.png";

export default function RecunoasteVocaleleJoc({setText, completeLevel}) {
    const cuvinte = [
        {cuvant: "cal", imagine: cal},
        {cuvant: "foc", imagine: foc},
        {cuvant: "cer", imagine: cer},
        {cuvant: "pix", imagine: pix},
        {cuvant: "fulg", imagine: fulg},
        {cuvant: "nas", imagine: nas},
    ];
    const [vocaleGasite, setVocaleGasite] = useState(cuvinte.map(cuv => false))

    useEffect(() => {
        console.log(vocaleGasite)
        if (vocaleGasite.filter(gasit => !gasit).length === 0)
            completeLevel()
    }, [vocaleGasite, completeLevel]);

    const vocalaGasita = (indexCuvant) => {
        console.log(`INDEX CUVANT: ${indexCuvant}`)
        setVocaleGasite(vocaleGasite.map((gasit, index) => index === indexCuvant ? true : gasit))
    }

    const chunkArray = (arr, size) => {
        const result = [];
        for (let i = 0; i < arr.length; i += size) {
            result.push(arr.slice(i, i + size));
        }
        return result;
    };

    const cuvinteInRows = chunkArray(cuvinte, 2);

    return (
        <div style={styles.pageContainer}>
            <div style={styles.contentWrapper}>
                {cuvinteInRows.map((row, rowIndex) => (
                    <div key={rowIndex} style={styles.row}>
                        {row.map((item, index) => (
                            <div key={index} style={styles.cell}>
                                <div style={styles.cuvantContainer}>
                                    <Cuvant cuvant={item.cuvant} setText={setText}
                                            vocalaGasita={() => vocalaGasita(index + (rowIndex) * 2)}/>
                                </div>
                                <img
                                    src={item.imagine}
                                    alt={item.cuvant}
                                    style={styles.image}
                                />
                            </div>
                        ))}
                    </div>
                ))}
            </div>
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
    contentWrapper: {
        width: "60%",
        height: "80%",
        display: "flex",
        flexDirection: "column",
        justifyContent: "center",
        alignItems: "center",
    },
    row: {
        display: "flex",
        flex: 1,
        flexDirection: "row",
        justifyContent: "center",
        alignItems: "center",
        gap: "10rem",
        marginBottom: 20,
        width: "100%",
    },
    cell: {
        display: "flex",
        flexDiresction: "row",
        alignItems: "center",
        justifyContent: "space-between",
        textAlign: "center",
        height: "100%",
        flex: 1,
    },
    cuvantContainer: {
        marginBottom: 10,
    },
    image: {
        height: "auto",
        width: "100%",
        maxWidth: "180px",
        maxHeight: "180px",
        objectFit: "contain",
    },
};

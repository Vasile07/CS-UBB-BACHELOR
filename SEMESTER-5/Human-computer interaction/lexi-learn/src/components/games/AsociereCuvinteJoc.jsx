import React, {useEffect, useState} from "react";

export default function AsociereCuvinteJoc({completeLevel, setText}) {
    const cuvinte = [{cuvant: "cal", pronuntie: "cal"}, {cuvant: "fata", pronuntie: "fată"}, {
        cuvant: "masina",
        pronuntie: "maşină"
    }, {cuvant: "pisica", pronuntie: "pisică"}]
    const [startDiv, setStartDiv] = useState(null);
    const [endDiv, setEndDiv] = useState(null);
    const [isDragging, setIsDragging] = useState(false);
    const [lines, setLines] = useState([]);
    const [currentMouse, setCurrentMouse] = useState(null);
    const [cuvinteAsociate, setCuvinteAsociate] = useState(cuvinte.map(({cuvant, pronuntie}) => {
        return {cuvant: cuvant, asociat: false}
    }))

    const handleMouseDown = (id) => {
        setStartDiv(id);
        setIsDragging(true);
        setCurrentMouse(null);
    };

    const handleMouseUp = (id) => {
        console.log(id)
        if (!isDragging) return;
        setEndDiv(id);
        setIsDragging(false);

        if (startDiv && id && startDiv + "_pronuntie" === id) {
            setLines((prevLines) => [...prevLines, {start: startDiv, end: id}]);

            setText(cuvinte.filter(({cuvant, pronuntie}) => cuvant === startDiv)[0].pronuntie)
            setCuvinteAsociate(cuvinteAsociate.map(({cuvant, asociat}) => {
                return cuvant === startDiv ? {cuvant, asociat: true} : {cuvant, asociat}
            }))
        } else {
            setText("Încearcă din nou!");
        }
        setStartDiv(null);
        setCurrentMouse(null);
    };

    const handleMouseMove = (e) => {
        const svgElement = document.querySelector("svg");
        const svgTop = svgElement.getBoundingClientRect().top;
        if (isDragging && startDiv) {
            setCurrentMouse({x: e.clientX, y: e.clientY - svgTop});
        }
    };

    const getCenterCoordinates = (id, type) => {
        const element = document.getElementById(id);
        const rect = element.getBoundingClientRect();
        const svgElement = document.querySelector("svg");
        const svgTop = svgElement.getBoundingClientRect().top;
        return {
            x: rect.left + rect.width / 2,
            y: rect.top - svgTop + (type === "start" ? rect.height - 20 : 0),
        };
    };

    const shuffleArray = (array) => {
        const shuffled = [...array];
        for (let i = shuffled.length - 1; i > 0; i--) {
            const j = Math.floor(Math.random() * (i + 1));
            [shuffled[i], shuffled[j]] = [shuffled[j], shuffled[i]];
        }
        return shuffled;
    };

    const [wordRowArray, setWordRowArray] = useState([]);
    const [imageRowArray, setImageRowArray] = useState([]);

    useEffect(() => {
        setWordRowArray(shuffleArray(cuvinte))
        setImageRowArray(shuffleArray(cuvinte))
    }, []);

    useEffect(() => {
        if (cuvinteAsociate.filter(({cuvant, asociat}) => asociat === false).length === 0)
            completeLevel();
    }, [completeLevel, cuvinte, cuvinteAsociate]);

    return (
        <div style={styles.pageContainer}
             onMouseMove={handleMouseMove}
             onMouseUp={() => {
                 setIsDragging(false);
                 setStartDiv(null);
                 setCurrentMouse(null);
             }}>
            <svg
                id={"svg"}
                style={{
                    position: "absolute",
                    top: 0,
                    left: 0,
                    width: "100%",
                    height: "100%",
                    pointerEvents: "none",
                }}
            >
                {/* Permanent lines */}
                {lines.map((line, index) => {
                    const startCoords = getCenterCoordinates(line.start, "start");
                    const endCoords = getCenterCoordinates(line.end, "end");
                    return (
                        <line
                            key={index}
                            x1={startCoords.x}
                            y1={startCoords.y}
                            x2={endCoords.x}
                            y2={endCoords.y}
                            stroke="black"
                            strokeWidth="2"
                            markerEnd="url(#arrowhead)"
                        />
                    );
                })}

                {isDragging && startDiv && currentMouse && (
                    <line
                        x1={getCenterCoordinates(startDiv, "start").x}
                        y1={getCenterCoordinates(startDiv, "start").y}
                        x2={currentMouse.x}
                        y2={currentMouse.y}
                        stroke="black"
                        strokeWidth="2"
                        markerEnd="url(#arrowhead)"
                    />
                )}

                <defs>
                    <marker
                        id="arrowhead"
                        markerWidth="10"
                        markerHeight="7"
                        refX="10"
                        refY="3.5"
                        orient="auto"
                    >
                        <polygon points="0 0, 10 3.5, 0 7" fill="black"/>
                    </marker>
                </defs>
            </svg>
            <div style={styles.wordsRow}>
                {
                    wordRowArray.map(({cuvant, pronuntie}) => {
                        return (
                            <div key={cuvant} id={cuvant} style={styles.word}
                                 onMouseDown={() => handleMouseDown(cuvant)}>
                                {
                                    cuvant.split('').map((letter, index) => {
                                        return (
                                            <p key={index}
                                               style={"aeiou".includes(letter) ? styles.vowel : styles.consonant}>{letter}</p>
                                        )
                                    })
                                }
                            </div>
                        )
                    })
                }
            </div>
            <div style={styles.imagesRows}>
                {
                    imageRowArray.map(({cuvant, pronuntie}) => {
                        return (
                            <div key={cuvant + "_pronuntie"} id={cuvant + "_pronuntie"} style={styles.image}
                                 onMouseUp={() => handleMouseUp(cuvant + "_pronuntie")}>
                                <img src={require(`../../assets/images/games/Asociere/${cuvant}.png`)} alt={cuvant}
                                     height={"100%"} width={"auto"}/>
                            </div>
                        )
                    })
                }
            </div>
        </div>
    )
}

const styles = {
    pageContainer: {
        width: "100%",
        display: "flex",
        flex: 1,
        flexDirection: "column",
        alignItems: "center",
        justifyContent: "center",
        position: "relative",
    },
    wordsRow: {
        width: "80%",
        height: "33%",
        display: "flex",
        flexDirection: "row",
        gap: 20,
        justifyContent: "space-around",
        alignItems: "center",
    },
    word: {
        userSelect: "none",
        display: "flex",
        flexDirection: "row",
        height: "fit-content"
    },
    vowel: {
        fontSize: "15vh",
        fontFamily: "Jomhuria, sans-serif",
        margin: 0,
        color: "#79AF2D"
    },
    consonant: {
        fontSize: "15vh",
        fontFamily: "Jomhuria, sans-serif",
        margin: 0,
        color: "#000"
    },
    imagesRows: {
        width: "80%",
        display: "flex",
        flex: 1,
        flexDirection: "row",
        justifyContent: "space-around",
        alignItems: "center",
    },
    image: {
        maxHeight: "250px",
        maxWidth: "250px",
        height: "40%",
        width: "auto",
        userSelect: "none",
    }
}
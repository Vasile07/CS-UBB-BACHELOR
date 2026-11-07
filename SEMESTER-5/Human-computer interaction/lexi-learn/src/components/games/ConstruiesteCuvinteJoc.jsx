import {useEffect, useRef, useState} from "react";

export default function ConstruiesteCuvinteJoc({setText, completeLevel}) {
    const [cuvinte, setCuvinte] = useState([]);
    const [currentIndex, setCurrentIndex] = useState(0);
    const [animateOption, setAnimateOption] = useState(null); // To track the option being animated
    const bridgeRef = useRef(null);
    const inceputRef = useRef(null);
    const bridgeTop = "65%";
    const [distanta, setDistanta] = useState({x: 0, y: 0});
    const [imagePosition, setImagePosition] = useState({left: null, bottom: null});

    useEffect(() => {
        const cuvs = [
            {
                inceput: "ma",
                optiuni: ["ma", "fa", "tu"],
                optiuneCorecta: "ma",
                imagine: require("../../assets/images/games/ConstruiesteCuvinte/mama.png"),
            },
            {
                inceput: "fa",
                optiuni: ["ta", "fe", "sa"],
                optiuneCorecta: "ta",
                imagine: require("../../assets/images/games/ConstruiesteCuvinte/fata.png"),
            },
            {
                inceput: "mu",
                optiuni: ["ra", "fo", "ma"],
                optiuneCorecta: "ra",
                imagine: require("../../assets/images/games/ConstruiesteCuvinte/mure.png"),
            },
            {
                inceput: "ta",
                optiuni: ["ma", "ta", "fa"],
                optiuneCorecta: "ta",
                imagine: require("../../assets/images/games/ConstruiesteCuvinte/tata.png"),
            },
            {
                inceput: "ma",
                optiuni: ["fo", "so", "sa"],
                optiuneCorecta: "sa",
                imagine: require("../../assets/images/games/ConstruiesteCuvinte/masa.png"),
            },
            {
                inceput: "sa",
                optiuni: ["fo", "ma", "re"],
                optiuneCorecta: "re",
                imagine: require("../../assets/images/games/ConstruiesteCuvinte/sare.png"),
            },
        ];

        const shuffleArray = (array) => {
            const shuffled = [...array];
            for (let i = shuffled.length - 1; i > 0; i--) {
                const j = Math.floor(Math.random() * (i + 1));
                [shuffled[i], shuffled[j]] = [shuffled[j], shuffled[i]];
            }
            return shuffled;
        };

        setCuvinte(shuffleArray(cuvs));
    }, []);

    const setTextCuvantCorect = (cuvant) => {
        setText(`Felicitări! Cuvântul construit este ${cuvant}.`);
    };

    const onCuvantClick = (cuvant) => {
        if (cuvant === cuvinte[currentIndex].optiuneCorecta) {
            setTextCuvantCorect(
                cuvinte[currentIndex].inceput + "\n" + cuvinte[currentIndex].optiuneCorecta
            );
            startAnimation(cuvant);
            setTimeout(() => {
                setCurrentIndex(currentIndex + 1);
            }, 6000);
        } else setText("Încearcă din nou!");
    };

    useEffect(() => {
        if (currentIndex === cuvinte.length && currentIndex !== 0) completeLevel();
    }, [completeLevel, currentIndex, cuvinte.length]);

    const startAnimation = (cuvant) => {
        setAnimateOption(cuvant);

        if (inceputRef.current) {
            const rect = inceputRef.current.getBoundingClientRect();
            const myOption = document.getElementById(cuvant).getBoundingClientRect();

            setDistanta({x: rect.left + rect.width - myOption.left, y: rect.top - myOption.top});
            setTimeout(() => {
                setImagePosition({
                    left: rect.left + rect.width + myOption.width + 60,
                    bottom: rect.bottom + rect.height / 2
                })
            }, 1000);
        }

        setTimeout(() => {
            setAnimateOption(null);
            setImagePosition({left: null, bottom: null})
        }, 5000);
    };

    useEffect(() => {
        console.log(imagePosition.left, imagePosition.bottom)
    }, [imagePosition])

    return (
        <div style={styles.pageContainer}>
            <img
                ref={bridgeRef}
                id={"bridge"}
                style={styles.bridge}
                alt={"Bridge"}
                src={require("../../assets/images/games/ConstruiesteCuvinte/bridge.png")}
            />
            {cuvinte.length > 0 && currentIndex < cuvinte.length && (
                <>
                    <div
                        ref={inceputRef}
                        style={{...styles.inceputCuvant, bottom: bridgeTop}}
                    >
                        {cuvinte[currentIndex].inceput}
                    </div>
                    <div style={styles.optiuni}>
                        {cuvinte[currentIndex].optiuni.map((optiune) => {
                            const isAnimating = animateOption === optiune;
                            return (
                                <div
                                    id={optiune}
                                    style={{
                                        ...styles.optiune,
                                        transition: "transform 1s ease-in-out",
                                        transform: isAnimating
                                            ? `translate(${distanta.x}px, ${distanta.y}px)` // Animate to the inceput div
                                            : "translateY(0)", // Reset to original position
                                    }}
                                    onClick={() => onCuvantClick(optiune)}
                                >
                                    {optiune}
                                </div>
                            );
                        })}
                    </div>
                    {
                        imagePosition.left && imagePosition.bottom &&
                        <img style={{
                            width: "auto",
                            height: "150px",
                            position: "absolute",
                            left: imagePosition.left,
                            bottom: imagePosition.bottom
                        }} src={cuvinte[currentIndex].imagine}
                             alt={cuvinte[currentIndex].inceput + cuvinte[currentIndex].optiuneCorecta}/>
                    }
                </>
            )}
        </div>
    );
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
    bridge: {
        width: "100%",
        maxHeight: "65%",
        position: "absolute",
        bottom: 0,
    },
    inceputCuvant: {
        position: "absolute",
        left: "50%",
        transform: "translate(-100%, 25%)",
        fontSize: 100,
        fontFamily: "Jomhuria, sans-serif",
        userSelect: "none",
    },
    optiuni: {
        position: "absolute",
        left: "50%",
        bottom: 0,
        transform: "translateX(-50%)",
        display: "flex",
        width: "60%",
        flexDirection: "row",
        justifyContent: "space-between",
        alignItems: "flex-end",
    },
    optiune: {
        userSelect: "none",
        fontSize: 100,
        fontFamily: "Jomhuria, sans-serif",
        cursor: "pointer",
    },
};

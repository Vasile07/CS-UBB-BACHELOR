import {useState} from "react";

export default function ClickableLetter({letter, setText, vocalaGasita}) {
    const [isVowel, setIsVowel] = useState(false);
    const [animate, setAnimate] = useState(false);
    const [shake, setShake] = useState(false); // New state for shaking effect

    const handleClick = () => {
        if ("aeiouAEIOU".includes(letter)) {
            if (letter === "e")
                setText("EEE")
            else
                setText(letter)
            setAnimate(true);
            setIsVowel(true);
            vocalaGasita()
            setTimeout(() => {
                setAnimate(false);
            }, 300);
        } else {
            setText("Încearcă din nou!")
            setIsVowel(false);
            setShake(true); // Trigger shake animation
            setTimeout(() => {
                setShake(false); // Reset shake animation
            }, 500); // Match shake animation duration
        }
    };

    return (
        <div
            style={{
                ...styles.baseStyle,
                ...(animate ? styles.animated : {}),
                ...(shake ? styles.shake : {}),
                ...(isVowel ? styles.correctLetter : styles.simpleLetter),
            }}
            onClick={handleClick}
        >
            <style>
                {`
                @keyframes scaleUp {
                    0% {
                        transform: scale(1);
                    }
                    50% {
                        transform: scale(1.2);
                    }
                    100% {
                        transform: scale(1);
                    }
                }

                @keyframes shake {
                    0% {
                        transform: rotate(0deg);
                    }
                    25% {
                        transform: rotate(-20deg);
                    }
                    50% {
                        transform: rotate(20deg);
                    }
                    75% {
                        transform: rotate(-20deg);
                    }
                    100% {
                        transform: rotate(0deg);
                    }
                }
                `}
            </style>
            {letter}
        </div>
    );
}

const styles = {
    baseStyle: {
        width: "fit-content",
        height: "fit-content",
        fontSize: "10vw",
        fontFamily: "Jomhuria, sans-serif",
        display: "inline-block",
        transition: "all 0.5s ease",
        cursor: "pointer",
        userSelect: "none",
    },
    simpleLetter: {
        color: "black",
    },
    correctLetter: {
        color: "#94DD2F",
        textShadow:
            "0 0 10px white, 0 0 10px white, 0 0 10px white, 0 0 10px white, 0 0 10px white, 0 0 10px white, 0 0 10px white, 0 0 10px white, 0 0 10px white",
    },
    animated: {
        animation: "scaleUp 0.3s ease",
    },
    shake: {
        animation: "shake 0.5s ease", // Reference to the shake animation
    },
};

import React from "react";
import hole from "../assets/images/games/Hole.png";
import grayLexi from "../assets/images/games/lexi-gray.png";

export default function LexiHole({isUp, onClick, text, factor}) {

    return (
        <div
            style={{
                width: "100%", height: "100%", position: "relative",
                cursor: "pointer"
            }}
            onClick={onClick} // Initialize and toggle
        >
            <style>
                {`
    @keyframes moveUp {
        0% {
            bottom: 0;
        }
        100% {
            bottom: 115%;
        }
    }

    @keyframes moveDown {
        0% {
            bottom: 115%;
        }
        100% {
            bottom: 0;
        }
    }
`}
            </style>

            <img src={hole} width={"100%"} height={"auto"} style={{...styles.hole, zIndex: 3 * factor}}/>
            <div style={{...styles.cover, zIndex: 2 * factor}}/>
            <div style={{
                ...styles.lexi,
                zIndex: 1 * factor,
                animation:
                    isUp === null
                        ? "none" // No animation on initial render
                        : isUp
                            ? "moveUp 1s ease-in-out forwards"
                            : "moveDown 1s ease-in-out forwards",
            }}>
                <p style={styles.lexiText}>{text}</p>
                <img
                    src={grayLexi}
                    width={"100%"}
                    height={"auto"}
                    style={{maxHeight: 200}}
                />
            </div>
        </div>
    );
}

const styles = {
    hole: {
        position: "absolute",
        bottom: "90%",
    },
    lexi: {
        width: "fit-content",
        height: "fit-content",
        display: "flex",
        flexDirection: "column",
        justifyContent: "center",
        alignItems: "center",
        position: "absolute",
        bottom: 0,
        left: "50%",
        transform: "translateX(-50%)", // Default position
    },
    cover: {
        backgroundColor: "#F5E3A9",
        position: "absolute",
        width: "100%",
        height: "100%",
    },
    lexiText: {
        margin: 0,
        fontSize: "6vh",
        fontFamily: "Jomhuria, sans-serif",
    }
};

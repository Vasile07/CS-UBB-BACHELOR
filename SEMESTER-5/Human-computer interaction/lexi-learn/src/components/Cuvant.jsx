import ClickableLetter from "./ClickableLetter";

export default function Cuvant({cuvant, setText, vocalaGasita}) {
    return (
        <div style={styles.cuvant}>
            {
                cuvant.split('').map((letter, index) => (
                    <ClickableLetter key={index} letter={letter} setText={setText} vocalaGasita={vocalaGasita}/>
                ))
            }
        </div>
    )
}

const styles = {
    cuvant: {
        width: "fit-content",
        height: "fit-content",
        display: "flex",
        flexDirection: "row",
        gap: 9,
    }
}
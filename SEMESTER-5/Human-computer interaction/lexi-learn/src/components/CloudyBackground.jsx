import clouds from '../assets/images/background/clouds.png';
import mountains from '../assets/images/background/mountain1.png';
import grass from '../assets/images/background/front.png';

export default function CloudyBackground() {
    return (
        <div style={styles.container}>
            <img src={clouds} alt="Clouds" style={styles.clouds} />
            <img src={mountains} alt="Mountains" style={styles.mountains} />
            <img src={grass} alt="Grass" style={styles.grass} />
        </div>
    );
}

const styles = {
    container: {
        bacgroundColor: "transparent",
        position: "relative", // Set position to relative for layering
        width: "100%",
        height: "100%",
        overflow: "hidden", // Ensures child elements stay within bounds
    },
    clouds: {
        position: "absolute",
        right: "-10%",
        top: 0,
        width: "120%",
        height: "100%", // Adjust as needed for visual alignment
    },
    mountains: {
        position: "absolute",
        bottom: "20%", // Push up slightly for visual stacking
        width: "100%",
        height: "55%",
        bottom: 0,
    },
    grass: {
        position: "absolute",
        bottom: 0,
        width: "100%",
        height: "50%", // Adjust for grass height
    },
};

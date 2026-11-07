import React, {useEffect, useState} from "react";
import {DragDropContext, Draggable, Droppable} from "react-beautiful-dnd";
import cos from "../../assets/images/games/CosLitere/cos.png";
import bone from "../../assets/images/games/CosLitere/bone.png"

const getRandomLetter = () => {
    const consonant = "bcdfghjklmnpqrstvwxyz";
    const vowels = "aeiou"
    const rand = Math.random();
    if (rand < 0.7)
        return vowels[Math.floor(Math.random() * vowels.length)]
    else
        return consonant[Math.floor(Math.random() * consonant.length)];
};

// Helper function to check for intersection between two positions
const isIntersecting = (pos1, pos2) => {
    return (
        Math.abs(pos1.top - pos2.top) < 10 && // Some threshold to consider close enough
        Math.abs(pos1.left - pos2.left) < 10
    );
};

const StrangeOaseleJoc = ({setText, completeLevel}) => {
    const [list1, setList1] = useState([]);
    const vowels = ["a", "e", "i", "o", "u"];
    const [vowelsList, setVowelsList] = useState({"a": [], "e": [], "i": [], "o": [], "u": []});

    useEffect(() => {
        const items = [];
        const numItems = 18;
        const positions = [];

        for (let i = 0; i < numItems; i++) {
            let newItem;
            let newPosition;

            // Generate a random letter and random position
            do {
                newItem = getRandomLetter();
                newPosition = {
                    top: Math.random() * 70 + 15,
                    left: Math.random() * 85 + 5,
                };
            } while (positions.some((pos) => isIntersecting(newPosition, pos)));

            // Add the item and its position to the list
            items.push({letter: newItem, ...newPosition});
            positions.push(newPosition);
        }

        // Set the list state
        setList1(items);
    }, []);

    const onDragEnd = (result) => {
        const {source, destination} = result;

        // If dropped outside a droppable area
        if (!destination) return;

        // Allow dragging only from "list1" to vowel lists
        if (source.droppableId !== "list1" || !vowels.includes(destination.droppableId)) {
            return;
        }

        // Move item from list1 to the corresponding vowel list
        const sourceItems = Array.from(list1); // Assuming `list1` is defined in state
        const destinationItems = Array.from(vowelsList[destination.droppableId]);

        const [movedItem] = sourceItems.splice(source.index, 1);
        if (!vowels.includes(movedItem.letter))
            setText("Aceea nu este o vocală. Mai încearcă!");
        else {
            if (movedItem.letter === destination.droppableId) {
                if (destinationItems.length === 0)
                    destinationItems.push(movedItem.letter);

                setList1(sourceItems); // Update list1
                setVowelsList({
                    ...vowelsList,
                    [destination.droppableId]: destinationItems,
                });

                setText(`Felicitări! Aceea este vocala ${movedItem.letter}.`);
            } else {
                setText("Coşul nu este cel potrivit vocalei, dar mai încearcă!");
            }
        }

    };

    useEffect(() => {
        if (list1.length > 0 && list1.filter((item) => vowels.includes(item.letter)).length === 0)
            completeLevel()
    }, [list1])

    return (
        <DragDropContext onDragEnd={onDragEnd}>
            <div
                style={{
                    overflow: "hidden",
                    display: "flex",
                    flexDirection: "column",
                    gap: "20px",
                    justifyContent: "center",
                    alignItems: "flex-end",
                    width: "100%",
                    height: "100%",
                }}
            >
                {/* List 1 */}
                <Droppable droppableId="list1">
                    {(provided) => (
                        <div
                            ref={provided.innerRef}
                            {...provided.droppableProps}
                            style={{
                                position: "relative",
                                width: "100%",
                                height: "75%",
                                display: "flex",
                                flexWrap: "wrap",
                                justifyContent: "center",
                                alignItems: "center",
                                gap: 15
                            }}
                        >
                            {list1.map((item, index) => (
                                <Draggable key={index} draggableId={`${item.letter}-${index}`} index={index}>
                                    {(provided) => (
                                        <div
                                            ref={provided.innerRef}
                                            {...provided.draggableProps}
                                            {...provided.dragHandleProps}
                                            style={{
                                                // position: "absolute",
                                                // top: `${item.top}%`,
                                                // left: `${item.left}%`,
                                                width: "150px",
                                                height: "120px",
                                                display: "flex",
                                                justifyContent: "center",
                                                alignItems: "center",
                                                backgroundImage: `url(${bone})`, // Correct usage of backgroundImage
                                                backgroundSize: "cover", // Optional: Adjust background image size
                                                backgroundPosition: "center",
                                                ...provided.draggableProps.style,
                                            }}
                                        >
                                            <p style={{
                                                fontSize: 30,
                                                fontWeight: "bold",
                                                margin: "0px 6px 10px 0px"
                                            }}>{item.letter}</p>

                                        </div>
                                    )}
                                </Draggable>
                            ))}
                            {provided.placeholder}
                        </div>
                    )}
                </Droppable>

                <div
                    style={{
                        width: "80%",
                        height: "25%",
                        display: "flex",
                        flexDirection: "row",
                        justifyContent: "space-between",
                        alignItems: "center",
                    }}
                >
                    {vowels.map((vowel) => (
                        <Droppable droppableId={vowel} key={vowel}>
                            {(provided) => (
                                <div
                                    ref={provided.innerRef}
                                    {...provided.droppableProps}
                                    style={{
                                        display: "flex",
                                        flexDirection: "column",
                                        width: "220px",
                                        height: "100%",
                                        position: "relative",
                                    }}
                                >
                                    <p
                                        style={{
                                            textAlign: "center",
                                            margin: 0,
                                            fontWeight: "bold",
                                            color: "#28a745",
                                            fontSize: "5vh",
                                        }}
                                    >
                                        {vowel}
                                    </p>
                                    {vowelsList[vowel].map((item, index) => (
                                        <Draggable key={item} draggableId={item} index={index}>
                                            {(provided) => (
                                                <div
                                                    ref={provided.innerRef}
                                                    {...provided.draggableProps}
                                                    {...provided.dragHandleProps}
                                                    style={{
                                                        position: "absolute",
                                                        width: "50%",
                                                        height: "50%",
                                                        top: "20%",
                                                        left: "20%",
                                                        transform: "translateX(-50%)",
                                                        textAlign: "center",
                                                        backgroundImage: `url(${bone})`, // Correct usage of backgroundImage
                                                        backgroundSize: "cover", // Optional: Adjust background image size
                                                        backgroundPosition: "center",
                                                        ...provided.draggableProps.style,

                                                    }}
                                                >
                                                    {/*<p style={{*/}
                                                    {/*    fontSize: 40,*/}
                                                    {/*    fontWeight: "bold",*/}
                                                    {/*    margin: 0,*/}
                                                    {/*}}>{item}</p>*/}
                                                </div>
                                            )}
                                        </Draggable>
                                    ))}
                                    {provided.placeholder}
                                    <div style={{
                                        width: "100%",
                                        height: "30px",
                                        backgroundColor: "#f8e4ac",
                                        position: "absolute",
                                        bottom: "0px"
                                    }}/>
                                    <img
                                        style={{
                                            width: "100%",
                                            position: "absolute",
                                            height: "100%",
                                            bottom: "5px",
                                        }}
                                        src={cos}
                                        alt="vowel-image"
                                    />
                                </div>
                            )}
                        </Droppable>
                    ))}
                </div>
            </div>
        </DragDropContext>
    );
};

export default StrangeOaseleJoc;

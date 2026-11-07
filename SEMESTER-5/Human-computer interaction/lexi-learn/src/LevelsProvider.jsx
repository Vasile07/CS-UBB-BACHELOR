import React, {createContext, useContext} from "react";

const LevelsContext = createContext([]);

export const LevelsProvider = ({children}) => {
    const levels = [
        {
            number: 1,
            path: "/levels/sunete-si-pronuntie"
        },
        {
            number: 2,
            path: "/levels/recunoaste-vocalele"
        },
        {
            number: 3,
            path: "/levels/strange-oasele"
        },
        {
            number: 4,
            path: "/levels/invata-silabe"
        },
        {
            number: 5,
            path: "/levels/construieste-cuvintele"
        },
        {
            number: 6,
            path: "/levels/potriveste-cuvinte"
        },
        {
            number: 7,
            path: "/levels/gaseste-diferenta"
        },
    ];

    return (
        <LevelsContext.Provider value={levels}>
            {children}
        </LevelsContext.Provider>
    );
};

export const useLevels = () => {
    const context = useContext(LevelsContext);
    if (!context) {
        throw new Error("useLevels must be used within a LevelsProvider");
    }
    return context;
};

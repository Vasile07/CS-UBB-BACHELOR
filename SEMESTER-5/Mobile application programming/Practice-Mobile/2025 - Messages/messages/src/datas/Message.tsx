export default interface Message{
    id: number,
    text: string,
    read: boolean,
    sender: string,
    created: number
}
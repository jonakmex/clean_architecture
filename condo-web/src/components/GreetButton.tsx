import GreetResponse from "../models/GreetResponse";

const GreetButton : React.FC = () => {

    const greetRequest = () => {
        fetch('http://localhost:8081/hello/jonathan')
        .then(response => {
            if (!response.ok) {
                throw new Error(response.statusText)
              }
              return response.json().then(data => data as GreetResponse);
        })
        .then(greetResponse => {
            console.log(greetResponse.greet);
        })
        .catch(exception => {
            console.log('ERROR');
        });
    }
    return <button onClick={greetRequest}>Send</button>
}

export default GreetButton;
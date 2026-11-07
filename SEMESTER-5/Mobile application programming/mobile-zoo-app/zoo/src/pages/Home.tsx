import { IonFab, IonFabButton, IonIcon, IonLabel, IonPage } from '@ionic/react';
import { add } from 'ionicons/icons';
import { useContext, useState } from 'react';
import AnimalList from '../components/AnimalList';
import AddPagePopup from './AddPagePopup';
import './Home.css';
import TabBar from '../components/TabBar';
import HorizontalNavBar from '../components/HorizontalNavBar';
import { AuthContext } from '../auth/AuthProvider';

const AnimalListPage: React.FC = () => {
  const [addWindowIsOpen, setAddWindowIsOpen] = useState(false);

  const { isAuthenticated, isAuthenticating, login, logout, authenticationError } = useContext(AuthContext);

  const handleAddAnimal = () => {
    setAddWindowIsOpen(true);
  };

  const closeAddModal = () => {
    setAddWindowIsOpen(false);
  };

  return (
    <IonPage className='home-page'>
      <HorizontalNavBar logout={() => {
        if (logout)
          logout()
      }} />
      <IonLabel className='welcome-label'>WELCOME TO THE ZOO</IonLabel>
      <AnimalList />
      <IonFab slot="fixed" vertical="bottom" horizontal="end">
        <IonFabButton onClick={handleAddAnimal}>
          <IonIcon icon={add} />
        </IonFabButton>
      </IonFab>
      <AddPagePopup onOpen={addWindowIsOpen} onClose={closeAddModal} />
    </IonPage>
  );
};

export default AnimalListPage;

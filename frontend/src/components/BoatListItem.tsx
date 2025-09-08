import { FC } from 'react';
import { Link } from 'react-router-dom';

interface BoatListItemProps {
  id: number;
  name: string;
  description: string;
  onDelete: (id: number) => void;
}

const BoatListItem: FC<BoatListItemProps> = ({ id, name, description, onDelete }) => {
  return (
    <div className="boat-item">
      <div className="boat-info">
        <span className="boat-name">{name}</span> -
        <span className="boat-description">{description}</span>
      </div>
      <div className="boat-buttons">
        <Link to={`/edit/${id}`}>
          <button className="edit-button">Edit</button>
        </Link>
        <button onClick={() => onDelete(id)} className="delete-button">Delete</button>
      </div>
    </div>
  );
};

export default BoatListItem;
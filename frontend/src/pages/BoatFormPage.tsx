import { useEffect, useState } from "react";
import { useNavigate, useParams } from "react-router-dom";
import axiosInstance from "../api/axiosInstance";

interface Boat {
  id?: number;
  name: string;
  description: string;
}

const BoatFormPage = () => {
  const { id } = useParams<{ id: string }>();
  const navigate = useNavigate();
  const [boat, setBoat] = useState<Boat>({ name: "", description: "" });

  useEffect(() => {
    if (id) {
      axiosInstance.get(`/boats/${id}`)
        .then(res => setBoat(res.data))
        .catch(err => console.error(err));
    }
  }, [id]);

  const handleChange = (e: React.ChangeEvent<HTMLInputElement | HTMLTextAreaElement>) => {
    setBoat({ ...boat, [e.target.name]: e.target.value });
  };

  const handleSubmit = (e: React.FormEvent) => {
    e.preventDefault();
    if (id) {
      axiosInstance.put(`/boats/${id}`, boat)
        .then(() => navigate("/boats"))
        .catch(err => console.error(err));
    } else {
      axiosInstance.post("/boats", boat)
        .then(() => navigate("/boats"))
        .catch(err => console.error(err));
    }
  };

  return (
    <div className="page-container">
      <form onSubmit={handleSubmit} className="form-card">
        <h2>{id ? "Edit Boat" : "Add New Boat"}</h2>
        <input name="name" placeholder="Boat Name" value={boat.name} onChange={handleChange} required />
        <textarea name="description" placeholder="Description" value={boat.description} onChange={handleChange} required />
        <button type="submit">{id ? "Update" : "Create"}</button>
      </form>
    </div>
  );
};

export default BoatFormPage;

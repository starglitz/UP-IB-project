import AxiosClient from "./clients/AxiosClient";

export const NurseService = {
    get,
    getAll,
    add
};


async function get(id) {
    return await AxiosClient.get(`http://localhost:8080/nurses/${id}`);
}

async function getAll() {
    return await AxiosClient.get(`http://localhost:8080/nurses`);
}

async function add(nurse) {
    return await AxiosClient.post("http://localhost:8080/nurses", nurse);
}

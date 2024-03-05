<script setup> 
    import { reactive, onMounted } from 'vue';
    import axios from 'axios';
    import FormInsert from '../reuse/FormInsert.vue';
    import router from '@/router';

    var objectInsertLaptop=reactive({
        idMarque : {},
idProcesseur : {},
idRam : {},
idDisque : {},

    });
    var responseInsertLaptop=reactive({});

            const allIdMarque=reactive({});
        const allIdProcesseur=reactive({});
        const allIdRam=reactive({});
        const allIdDisque=reactive({});


    onMounted(() => {
                const getAllIdMarque = async () => {
            try {
                const urlApiSpringBoot = 'http://localhost:8080/marque/allMarque';
                const response = await axios.get(urlApiSpringBoot);
                Object.assign(allIdMarque, response.data);
            } catch (error) {
                console.log(error.response);
            }
        };
        
        getAllIdMarque();
        const getAllIdProcesseur = async () => {
            try {
                const urlApiSpringBoot = 'http://localhost:8080/processeur/allProcesseur';
                const response = await axios.get(urlApiSpringBoot);
                Object.assign(allIdProcesseur, response.data);
            } catch (error) {
                console.log(error.response);
            }
        };
        
        getAllIdProcesseur();
        const getAllIdRam = async () => {
            try {
                const urlApiSpringBoot = 'http://localhost:8080/ram/allRam';
                const response = await axios.get(urlApiSpringBoot);
                Object.assign(allIdRam, response.data);
            } catch (error) {
                console.log(error.response);
            }
        };
        
        getAllIdRam();
        const getAllIdDisque = async () => {
            try {
                const urlApiSpringBoot = 'http://localhost:8080/disque/allDisque';
                const response = await axios.get(urlApiSpringBoot);
                Object.assign(allIdDisque, response.data);
            } catch (error) {
                console.log(error.response);
            }
        };
        
        getAllIdDisque();
 
    });

    const handleSubmit = () => {
        try {
            const url="http://localhost:8080/laptop/insertLaptop";
            const response=axios.post(url, objectInsertLaptop);
            response.then(reponse => {
                Object.assign(responseInsertLaptop, reponse.data);
                console.log(responseInsertLaptop);
            })
            .catch(errors => {
                console.log(errors);
            })
            router.push("/allLaptop");
        } catch(error) {
            console.log(error);
        }
    };
</script>
<template>
    <FormInsert title-props="Inserer une nouvelle laptop" :handle-submit="handleSubmit" submit-message="Inserer la  laptop">
        <input v-model="objectInsertLaptop.model" type="text">
<select  v-model="objectInsertLaptop.idMarque.idMarque" id="">
    <option v-for="(idMarque) in allIdMarque" :key="idMarque.idMarque" :value="idMarque.idMarque">
        {{ idMarque.label }}
    </option>
</select>
<select  v-model="objectInsertLaptop.idProcesseur.idProcesseur" id="">
    <option v-for="(idProcesseur) in allIdProcesseur" :key="idProcesseur.idProcesseur" :value="idProcesseur.idProcesseur">
        {{ idProcesseur.label }}
    </option>
</select>
<select  v-model="objectInsertLaptop.idRam.idRam" id="">
    <option v-for="(idRam) in allIdRam" :key="idRam.idRam" :value="idRam.idRam">
        {{ idRam.label }}
    </option>
</select>
<input v-model="objectInsertLaptop.tailleEcran" type="number">
<select  v-model="objectInsertLaptop.idDisque.idDisque" id="">
    <option v-for="(idDisque) in allIdDisque" :key="idDisque.idDisque" :value="idDisque.idDisque">
        {{ idDisque.label }}
    </option>
</select>
<input v-model="objectInsertLaptop.prixAchat" type="number">
<input v-model="objectInsertLaptop.prixVente" type="number">

    </FormInsert>
</template>
<style scoped>
    @import "@/assets/css/formulaire.css";
</style>

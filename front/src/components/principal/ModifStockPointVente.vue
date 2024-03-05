<script setup>
    import { onMounted, reactive } from 'vue';
    import axios from 'axios';
    import FormInsert from '../reuse/FormInsert.vue';
    import { useRoute } from 'vue-router';
    import router from '@/router';

    const route=useRoute();
    const objectInsertStockPointVente = reactive({
        idStockPointVente:route.query.idStockPointVente,
        idPointVente : {},
idLaptop : {},

    });

    const responseInsertStockPointVente = reactive({});
    
            const allIdPointVente=reactive({});
        const allIdLaptop=reactive({});


    onMounted(() => {
                const getAllIdPointVente = async () => {
            try {
                const urlApiSpringBoot = 'http://localhost:8080/pointVente/allPointVente';
                const response = await axios.get(urlApiSpringBoot);
                Object.assign(allIdPointVente, response.data);
            } catch (error) {
                console.log(error.response);
            }
        };
        
        getAllIdPointVente();
        const getAllIdLaptop = async () => {
            try {
                const urlApiSpringBoot = 'http://localhost:8080/laptop/allLaptop';
                const response = await axios.get(urlApiSpringBoot);
                Object.assign(allIdLaptop, response.data);
            } catch (error) {
                console.log(error.response);
            }
        };
        
        getAllIdLaptop();
 
    });

    const handleSubmit = async () => {
        try {
            const url = 'http://localhost:8080/stockPointVente/insertStockPointVente';
            const response = await axios.post(url, objectInsertStockPointVente);
            Object.assign(responseInsertStockPointVente, response);
            router.push("/allStockPointVente");
        } catch (error) {
            console.log(error);
        }
    };
</script>
<template>
    <FormInsert title-props="Modifier le/la stockPointVente" :handle-submit="handleSubmit" submit-message="Modifier">
        <select  v-model="objectInsertStockPointVente.idPointVente.idPointVente" id="">
    <option v-for="(idPointVente) in allIdPointVente" :key="idPointVente.idPointVente" :value="idPointVente.idPointVente">
        {{ idPointVente.label }}
    </option>
</select>
<select  v-model="objectInsertStockPointVente.idLaptop.idLaptop" id="">
    <option v-for="(idLaptop) in allIdLaptop" :key="idLaptop.idLaptop" :value="idLaptop.idLaptop">
        {{ idLaptop.label }}
    </option>
</select>
<input v-model="objectInsertStockPointVente.quantiteLaptopEntrant" type="number">
<input v-model="objectInsertStockPointVente.quantiteLaptopSortant" type="number">
<input v-model="objectInsertStockPointVente.dateMouvement" type="date">
<input v-model="objectInsertStockPointVente.prixAchat" type="number">
<input v-model="objectInsertStockPointVente.prixVente" type="number">

    </FormInsert>
</template>
<style scoped>
    @import "@/assets/css/formulaire.css";
</style>

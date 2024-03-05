<script setup>
    import { onMounted, reactive } from 'vue';
    import axios from 'axios';
    import FormInsert from '../reuse/FormInsert.vue';
    import { useRoute } from 'vue-router';
    import router from '@/router';

    const route=useRoute();
    const objectInsertStockLaptopMagasin = reactive({
        idStockLaptopMagasin:route.query.idStockLaptopMagasin,
        idLaptop : {},

    });

    const responseInsertStockLaptopMagasin = reactive({});
    
            const allIdLaptop=reactive({});


    onMounted(() => {
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
            const url = 'http://localhost:8080/stockLaptopMagasin/insertStockLaptopMagasin';
            const response = await axios.post(url, objectInsertStockLaptopMagasin);
            Object.assign(responseInsertStockLaptopMagasin, response);
            router.push("/allStockLaptopMagasin");
        } catch (error) {
            console.log(error);
        }
    };
</script>
<template>
    <FormInsert title-props="Modifier le/la stockLaptopMagasin" :handle-submit="handleSubmit" submit-message="Modifier">
        <select  v-model="objectInsertStockLaptopMagasin.idLaptop.idLaptop" id="">
    <option v-for="(idLaptop) in allIdLaptop" :key="idLaptop.idLaptop" :value="idLaptop.idLaptop">
        {{ idLaptop.label }}
    </option>
</select>
<input v-model="objectInsertStockLaptopMagasin.quantiteLaptopEntrant" type="number">
<input v-model="objectInsertStockLaptopMagasin.quantiteLaptopSortant" type="number">
<input v-model="objectInsertStockLaptopMagasin.dateMouvement" type="date">
<input v-model="objectInsertStockLaptopMagasin.prixAchat" type="number">
<input v-model="objectInsertStockLaptopMagasin.prixVente" type="number">

    </FormInsert>
</template>
<style scoped>
    @import "@/assets/css/formulaire.css";
</style>

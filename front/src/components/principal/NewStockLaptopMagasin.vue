<script setup> 
    import { reactive, onMounted } from 'vue';
    import axios from 'axios';
    import FormInsert from '../reuse/FormInsert.vue';
    import router from '@/router';

    var objectInsertStockLaptopMagasin=reactive({
        idLaptop : {},

    });
    var responseInsertStockLaptopMagasin=reactive({});

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

    const handleSubmit = () => {
        try {
            const url="http://localhost:8080/stockLaptopMagasin/insertStockLaptopMagasin";
            const response=axios.post(url, objectInsertStockLaptopMagasin);
            response.then(reponse => {
                Object.assign(responseInsertStockLaptopMagasin, reponse.data);
                console.log(responseInsertStockLaptopMagasin);
            })
            .catch(errors => {
                console.log(errors);
            })
            router.push("/allStockLaptopMagasin");
        } catch(error) {
            console.log(error);
        }
    };
</script>
<template>
    <FormInsert title-props="Inserer une nouvelle stockLaptopMagasin" :handle-submit="handleSubmit" submit-message="Inserer la  stockLaptopMagasin">
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

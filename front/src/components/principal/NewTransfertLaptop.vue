<script setup> 
    import { reactive, onMounted } from 'vue';
    import axios from 'axios';
    import FormInsert from '../reuse/FormInsert.vue';
    import router from '@/router';

    var objectInsertTransfertLaptop=reactive({
        idPointVente : {},
idLaptop : {},

    });
    var responseInsertTransfertLaptop=reactive({});

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

    const handleSubmit = () => {
        try {
            const url="http://localhost:8080/transfertLaptop/insertTransfertLaptop";
            const response=axios.post(url, objectInsertTransfertLaptop);
            response.then(reponse => {
                Object.assign(responseInsertTransfertLaptop, reponse.data);
                console.log(responseInsertTransfertLaptop);
            })
            .catch(errors => {
                console.log(errors);
            })
            router.push("/allTransfertLaptop");
        } catch(error) {
            console.log(error);
        }
    };
</script>
<template>
    <FormInsert title-props="Inserer une nouvelle transfertLaptop" :handle-submit="handleSubmit" submit-message="Inserer la  transfertLaptop">
        <select  v-model="objectInsertTransfertLaptop.idPointVente.idPointVente" id="">
    <option v-for="(idPointVente) in allIdPointVente" :key="idPointVente.idPointVente" :value="idPointVente.idPointVente">
        {{ idPointVente.label }}
    </option>
</select>
<select  v-model="objectInsertTransfertLaptop.idLaptop.idLaptop" id="">
    <option v-for="(idLaptop) in allIdLaptop" :key="idLaptop.idLaptop" :value="idLaptop.idLaptop">
        {{ idLaptop.label }}
    </option>
</select>
<input v-model="objectInsertTransfertLaptop.quantiteLaptop" type="number">
<input v-model="objectInsertTransfertLaptop.dateTransfert" type="date">
<input v-model="objectInsertTransfertLaptop.etatTransfert" type="number">

    </FormInsert>
</template>
<style scoped>
    @import "@/assets/css/formulaire.css";
</style>

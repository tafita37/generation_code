<script setup>
    import { onMounted, reactive, getCurrentInstance  } from 'vue';
    import axios from 'axios';
    import TitleListe from '../reuse/TitleListe.vue';
    import ListeGenerale from '../reuse/ListeGenerale.vue';
    import H5TitleListe from '../reuse/H5TitleListe.vue';
    import SpanListe from '../reuse/SpanListe.vue';
    import ModifSupprButton from '../reuse/ModifSupprButton.vue';
    import router from '@/router';

    const allTransfertLaptop=reactive({});

    const getAllTransfertLaptop = async () => {
        try {
            const urlApiSpringBoot = 'http://localhost:8080/transfertLaptop/allTransfertLaptop';
            const response = await axios.get(urlApiSpringBoot);
            Object.assign(allTransfertLaptop, response.data);
        } catch (error) {
            console.log(error.response);
        }
    };

    const loadFormModif = (idTransfertLaptop) => {
        router.push({path : "/modifTransfertLaptop", query: {idTransfertLaptop : idTransfertLaptop}});
    }

    const deleteTransfertLaptopById = (idTransfertLaptop) => {
        try {
            const urlApiSpringBoot = 'http://localhost:8080/transfertLaptop/deleteTransfertLaptopById?idTransfertLaptop='+idTransfertLaptop;
            const response = axios.get(urlApiSpringBoot);
            getCurrentInstance().proxy.forceUpdate();
        } catch (error) {
            console.error('Erreur lors de l\'appel de l\'API', error);
        }
    }

    onMounted(getAllTransfertLaptop);
</script>
<template>
    <TitleListe title-liste="Liste des transfertLaptop"/>
    <ListeGenerale>
        <div class="child_computer" v-for="(transfertLaptop)  in allTransfertLaptop" :key="transfertLaptop.idTransfertLaptop">
            <H5TitleListe labels="" :title-h5="transfertLaptop.idTransfertLaptop"/>
            <SpanListe :title-span='transfertLaptop.idTransfertLaptop' labels=''/><SpanListe :title-span='transfertLaptop.idPointVente.label' labels=''/><SpanListe :title-span='transfertLaptop.idLaptop.label' labels=''/><SpanListe :title-span='transfertLaptop.quantiteLaptop' labels=''/><SpanListe :title-span='transfertLaptop.dateTransfert' labels=''/><SpanListe :title-span='transfertLaptop.etatTransfert' labels=''/>
            <ModifSupprButton :funct-modif="() => loadFormModif(transfertLaptop.idTransfertLaptop)" :funct-delete="() => deleteTransfertLaptopById(transfertLaptop.idTransfertLaptop)"/>
        </div>
    </ListeGenerale>
</template>
<style scoped>
    @import "@/assets/css/liste.css";
</style>

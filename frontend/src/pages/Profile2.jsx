import "./Profile2.css"
import ProfileInfo from "../components/ProfileInfo";
import progressbar2 from "../assets/progressbar2.png"
import { useNavigate } from "react-router-dom";
import Button from "../components/Button"
import LivingBtn from "../components/LivingBtn";
import livingimg1 from "../assets/physical.png"
import livingimg2 from "../assets/visual.png"
import livingimg3 from "../assets/hearing.png"
import livingimg4 from "../assets/infants.png"
import livingimg5 from "../assets/senior.png"
import livingimg6 from "../assets/nothing.png"


const Profile2 = () => {
    const navigate = useNavigate();
    return(
        <section className="livingpage">
            <ProfileInfo text={"여행지 추천에 필요한 생활 정보를 선택해주세요."} />
            <section className="progressbar2">
                <img src={progressbar2} className='progressbar2' />
            </section>
            
            <section className='profile2'>
                <section className="livingbtns">
                    <LivingBtn src={livingimg1} text="지체장애" />
                    <LivingBtn src={livingimg2} text="시각장애" />
                    <LivingBtn src={livingimg3} text="청각장애" />
                    <LivingBtn src={livingimg4} text="영유아가족" />
                    <LivingBtn src={livingimg5} text="고령인" />
                    <LivingBtn src={livingimg6} text="해당없음" />
                </section>

                <section className="subbtn">
                    <Button text={"다음"} onClick={()=>{
                        navigate(`/profile3`)
                    }} />
                </section>


            </section>

        </section>
    );
};

export default Profile2;
package com.quynh.ai_skin_diagnosis;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class TipsFragment extends Fragment {

    RecyclerView recyclerTips;

    @Override
    public View onCreateView(LayoutInflater inflater,
                             ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(
                R.layout.activity_tips_fragment,
                container,
                false
        );

        recyclerTips = view.findViewById(R.id.recyclerTips);

        ArrayList<TipItem> list = new ArrayList<>();

        list.add(new TipItem(
                R.drawable.tip_food,
                "Chế độ ăn uống lành mạnh",
                "Một làn da đẹp thực chất phải được nuôi dưỡng sâu từ bên trong cơ thể.\n\n" +
                        "Hãy bổ sung vào thực đơn hàng ngày thật nhiều rau xanh, trái cây tươi (như cam, bưởi, quả mọng) để cung cấp vitamin C và chất chống oxy hóa.\n\n" +
                        "Đồng thời, bạn cần duy trì thói quen uống từ 1.5 đến 2 lít nước mỗi ngày nhằm giúp da luôn căng mọng, hỗ trợ quá trình đào thải độc tố.\n\n" +
                        "Đặc biệt, hãy cố gắng hạn chế tối đa đồ ăn cay nóng, nhiều dầu mỡ, đồ ngọt và các chất kích thích như cà phê, rượu bia. " +
                        "Chúng chính là những tác nhân hàng đầu kích thích tuyến bã nhờn hoạt động mạnh và gây ra mụn bọc, mụn viêm dai dẳng."
        ));

        list.add(new TipItem(
                R.drawable.tip_clean,
                "Vệ sinh da đúng cách",
                "Làm sạch là bước nền tảng và quan trọng nhất trong mọi chu trình chăm sóc da.\n\n" +
                        "Bạn nên duy trì thói quen rửa mặt đều đặn 2 lần mỗi ngày (vào buổi sáng sau khi thức dậy và buổi tối trước khi đi ngủ) bằng sữa rửa mặt có độ pH dịu nhẹ (khoảng 5.5).\n\n" +
                        "Khi rửa mặt, hãy massage thật nhẹ nhàng theo chuyển động tròn từ dưới lên trên, tránh chà xát quá mạnh tay làm tổn thương hàng rào bảo vệ da.\n\n" +
                        "Ngoài ra, ngay cả khi bạn không trang điểm, việc tẩy trang vào cuối ngày vẫn là bắt buộc để loại bỏ hoàn toàn bụi mịn, kem chống nắng và dầu thừa tích tụ sâu trong lỗ chân lông suốt cả ngày dài."
        ));

        list.add(new TipItem(
                R.drawable.tip_sleep,
                "Ngủ đủ giấc & Đúng giờ",
                "Ban đêm là khoảng thời gian vàng để cơ thể tái tạo tế bào và chữa lành các tổn thương trên da.\n\n" +
                        "Hãy tập thói quen đi ngủ trước 23h và đảm bảo ngủ đủ từ 7 đến 8 tiếng mỗi ngày. Việc thức khuya sẽ làm hormone cortisol (stress) gia tăng, khiến da dễ bị sạm màu, quầng thâm mắt xuất hiện và kích ứng mụn bùng phát.\n\n" +
                        "Một giấc ngủ sâu và chất lượng sẽ giúp lưu thông máu dưới da tốt hơn, mang lại cho bạn một làn da rạng rỡ, hồng hào và tràn đầy sức sống vào sáng hôm sau.\n\n" +
                        "Mẹo nhỏ: Hãy thay vỏ gối định kỳ 1 tuần/lần vì vỏ gối bẩn là nơi trú ngụ của hàng triệu vi khuẩn gây mụn đấy nhé!"
        ));

        list.add(new TipItem(
                R.drawable.tip_protect,
                "Sử dụng kem chống nắng hàng ngày",
                "Tia UV từ ánh nắng mặt trời chính là 'kẻ thù số một' gây ra tình trạng lão hóa sớm, tàn nhang và làm thâm mụn nặng nề hơn.\n\n" +
                        "Dù trời nắng hay âm u, thậm chí là khi bạn chỉ ngồi trong văn phòng làm việc với máy tính, việc thoa kem chống nắng vẫn là bước bắt buộc không thể bỏ qua.\n\n" +
                        "Hãy lựa chọn loại kem chống nắng phổ rộng có chỉ số SPF từ 30 đến 50 và PA+++ trở lên để bảo vệ da toàn diện nhất.\n\n" +
                        "Lưu ý: Bạn cần thoa kem trước khi ra ngoài từ 15-20 phút, sử dụng đủ một lượng bằng khoảng 1 đồng xu cho toàn mặt và nhớ thoa lại sau mỗi 2 đến 3 tiếng nếu phải hoạt động liên tục ngoài trời hoặc ra nhiều mồ hôi."
        ));

        list.add(new TipItem(
                R.drawable.tip_check,
                "Tự kiểm tra da định kỳ (Quy tắc ABCDE)",
                "Việc chủ động tự kiểm tra các nốt ruồi, vết đốm lạ trên cơ thể là cách hiệu quả nhất để phân biệt tổn thương lành tính và phát hiện sớm nguy cơ ác tính.\n\n" +
                        "Hãy ghi nhớ quy tắc ABCDE tiêu chuẩn trong da liễu để theo dõi các nốt bất thường:\n" +
                        "– A (Asymmetry - Bất đối xứng): Nốt ruồi lành tính thường tròn và đối xứng, trong khi u ác tính có hai nửa hình dạng hoàn toàn khác nhau.\n" +
                        "– B (Border - Đường viền): Tổn thương lành tính có viền mờ, mịn và rõ ràng. Khối u ác tính thường có viền nham nhở, hình răng cưa hoặc bị mờ nhòe.\n" +
                        "– C (Color - Màu sắc): Nốt ruồi an toàn thường chỉ có một màu đồng nhất (nâu hoặc đen). Dấu hiệu nguy hiểm là khi nốt đó có nhiều màu pha lẫn như chỗ đậm, chỗ nhạt, hoặc có sắc đỏ, xanh, trắng.\n" +
                        "– D (Diameter - Đường kính): Cần đặc biệt lưu ý nếu nốt ruồi có kích thước lớn hơn 6mm (bằng khoảng đầu tẩy của bút chì).\n" +
                        "– E (Evolving - Sự thay đổi): Đây là yếu tố quan trọng nhất. Nếu một nốt ruồi đột ngột thay đổi nhanh chóng về kích thước, hình dạng, màu sắc, hoặc có biểu hiện ngứa, chảy máu, đóng vảy... thì đó là tín hiệu báo động.\n\n" +
                        "Lời khuyên: Mỗi tháng một lần, bạn nên dành vài phút đứng trước gương lớn để quan sát toàn bộ cơ thể. Nếu phát hiện bất kỳ dấu hiệu 'bất thường' nào thuộc nhóm ABCDE, hãy đến gặp bác sĩ da liễu ngay lập tức để được chẩn đoán lâm sàng chính xác nhất."
        ));
        recyclerTips.setLayoutManager(
                new LinearLayoutManager(getContext())
        );

        recyclerTips.setAdapter(
                new TipsAdapter(getContext(), list)
        );

        return view;
    }
}
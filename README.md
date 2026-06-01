# ỨNG DỤNG ANDROID NHẬN DIỆN BỆNH DA LÀNH TÍNH - ÁC TÍNH BẰNG AI
## Ứng dụng sử dụng mô hình mạng neural tích chập EfficientNetV2B0 tối ưu cho các ứng dụng trên di động đã được huấn luyện sẵn. Khi sử dụng để chuẩn đoán bệnh về da lành tính hay ác tính, mô hình được huấn luyện thêm trên tập dữ liệu SkinDataSet được lấy trên Kaggle tại địa chỉ (https://www.kaggle.com/datasets/fanconic/skin-cancer-malignant-vs-benign)
## Trước khi vào link Colab, bạn hãy truy cập vào folder MachineLearning chứa dataset và Chọn Add shortcut to Drive (Thêm lối tắt vào Drive), đặt shortcut ở MyDrive để tranh bị lỗi khi vào file Colab. [MachineLearning (https://drive.google.com/drive/folders/1ziS9nsX8OTKJMO7qcI5ulrs1nE1bJ77H?usp=sharing)
## Mô hình dự đoán nhãn lớp (lành tính, ác tính) được huấn luyện trên Google Colab [chi tiết](https://colab.research.google.com/drive/122Pw-xLSXzvfOo-VOXQbkbG7ygtB5hcr?authuser=1#scrollTo=uiBC3TfpXGXd)
## Dựa vào hình ảnh mà người dùng cung cấp, ứng dụng sẽ cho bạn biết được bệnh da ấy có mức độ lành tính, ác tính bao nhiêu. Từ đó người dùng có thể chủ động thăm khám bác sĩ trước khi quá muộn đồng thời nâng cao ý thức bảo vệ làn da của bản thân.
## 1. Màn hình chính 
### Là nơi để người dùng chụp ảnh hoặc sử dụng ảnh từ thư viện để tiến hành kiểm tra phân tích. 
*Lưu ý: Ảnh khi chụp phải rõ nét, sát vào phần da cần kiểm tra, không bị lẫn phông nền dẫn đến kết quả thiếu tính chính xác.*
<img width="1280" height="800" alt="image" src="https://github.com/user-attachments/assets/16b62692-4909-4be1-8713-0cf51c9efb3e" />

### Kết quả sau khi người dùng nhấn vào nút phân tích sẽ trả về chỉ số mà AI phân tích được.
<img width="320" height="604" alt="image" src="https://github.com/user-attachments/assets/15d8a1ac-b6c2-4ff3-91c5-46d2d581cf5c" />

## 2. Màn hình lịch sử
### Hình ảnh sau khi được người dùng nhấn nút phân tích sẽ được lưu vào trong màn hình lịch sử của ứng dụng bằng SharedPreferences để lưu dữ liệu dưới dạng key-value và RecyclerView được sử dụng để hiển thị lịch sử các kết quả kèm kết quả và thời gian thực.
<img width="1280" height="800" alt="image" src="https://github.com/user-attachments/assets/fb07cc9a-45fd-4a29-9d23-3fb640dcaeb4" />

## 3. Màn hình thông tin về bệnh 
### Màn hình này hiển thị các bệnh lành tính - ác tính để người dùng có thể đọc và tìm hiểu về các bệnh. Sử dụng RecyclerView và hiện thị ra nhiều section theo chủ đề khác nhau.
<img width="1280" height="800" alt="image" src="https://github.com/user-attachments/assets/dff1a349-02ed-4cd8-a050-8bcebf70b61f" />

### Khi người dùng click vào từng item. Hệ thống sử dụng WebView để chuyển sang trang Web đã được tích hợp vào trong item đó để hiển thị thông tin chính thống cho người dùng đọc.
### Màn hình chuyển sang nội dung trang Web bệnh viêm da cơ địa.
<img width="341" height="610" alt="image" src="https://github.com/user-attachments/assets/92f90db4-a5c0-44a8-9ef1-81b62fc36c1b" />

## 4. Màn hình Tips
### Màn hình Tips sử dụng recycler view để hiển thị các cách giúp cải thiện các bệnh da ác tính, cách bảo vệ và chăm sóc da trước những nguy cơ tiềm tàng.
<img width="1280" height="800" alt="image" src="https://github.com/user-attachments/assets/47329c24-51d0-41e9-8fb0-8c308e971e78" />


### Màn hình chi tiết khi click vào từng item, khác với màn hình thông tin về các bệnh thì ở fragment này tích hợp thêm empty view actitvity - TipDetail mới để hiển thị thông tin.
<img width="305" height="590" alt="image" src="https://github.com/user-attachments/assets/e5fb683e-41a5-4009-a64c-b795dae7bc4f" />




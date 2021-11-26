#include <jni.h>
#include <string>
#include <vector>
#include <thread>
#include <deque>
#include <android/log.h>
#include <sys/mman.h>
#include <sys/prctl.h>

#define NOINLINE __attribute__((noinline))
#define LOG_TAG "NativeLeakTest"
#define NR_TEST_THREAD 10
#define NR_TEST_CASE 10
#define KB(x) ((x) * 1024L)
#define MB(x) (KB(x) * 1024L)
#define LOGI(...) __android_log_print(ANDROID_LOG_INFO, LOG_TAG, __VA_ARGS__)

static NOINLINE void TestMallocLeak() {
    for (int i = 0; i < NR_TEST_CASE; i++) {
        size_t size = MB(std::rand() % 10);
        auto ptr = malloc(size);
        if (i % 2) { // 有一部分并不释放空间
            LOGI("%s %p size %u", __FUNCTION__, ptr, size);
        } else {
            free(ptr);
        }
    }
}

static NOINLINE void TestReallocLeak() {
    for (int i = 0; i < NR_TEST_CASE; i++) {
        size_t size = KB(std::rand() % 1000);
        auto ptr = realloc(nullptr, size);
        if (i % 2) {
            LOGI("%s %p size %u", __FUNCTION__, ptr, size);
        } else {
            free(ptr);
        }
    }
}

static NOINLINE void TestCallocLeak() {
    for (int i = 0; i < NR_TEST_CASE; i++) {
        size_t size = std::rand() % 1000;
        auto ptr = calloc(size, 8);
        if (i % 2) {
            LOGI("%s %p size %u", __FUNCTION__, ptr, size);
        } else {
            free(ptr);
        }
    }
}

static NOINLINE void TestMemalignLeak() {
    for (int i = 0; i < NR_TEST_CASE; i++) {
        size_t size = KB(std::rand() % 1000);
        auto ptr = memalign(16, size);
        if (i % 2) {
            LOGI("%s %p size %u", __FUNCTION__, ptr, size);
        } else {
            free(ptr);
        }

    }
}

static NOINLINE void TestNewLeak() {
    for (int i = 0; i < NR_TEST_CASE; i++) {
        auto str_ptr = new std::string("test_leak_string");
        if (i % 2) {
            LOGI("%s %p size %u", __FUNCTION__, str_ptr, str_ptr->size());
        } else {
            delete str_ptr;
        }
    }
}

static NOINLINE void TestNewArrayLeak() {
    for (int i = 0; i < NR_TEST_CASE; i++) {
        size_t size = std::rand() % 1000;
        auto array_ptr = new std::string[size];
        if (i % 2) {
            LOGI("%s %p size %u", __FUNCTION__, array_ptr, size * sizeof(std::string));
        } else {
            delete[] array_ptr;
        }
    }
}

static NOINLINE void TestContainerLeak() {
    std::vector<std::string *> str_vector(NR_TEST_CASE);
    for (int i = 0; i < NR_TEST_CASE; i++) {
        str_vector[i] = new std::string("test_leak_container");
    }
}

static NOINLINE jlong TestJavaRefNative() {
    auto native_ptr = (void **) malloc(sizeof(void *) * NR_TEST_CASE);
    LOGI("%s Holder addr %p", __FUNCTION__, native_ptr);
    for (int i = 0; i < NR_TEST_CASE; i++) {
        size_t size = std::rand() % 1000;
        native_ptr[i] = malloc(size);
        LOGI("%s %p size %u", __FUNCTION__, native_ptr[i], size);
    }
    return reinterpret_cast<jlong>(native_ptr);
}

extern "C" JNIEXPORT jlong
Java_com_kwai_koom_demo_nativeleak_NativeLeakTest_triggerLeak(
        JNIEnv *env,
        jclass,
        jobject unuse/* this */) {
    auto leak_test = []() {
        TestMallocLeak();   // 有一部分malloc之后，不释放空间
        TestCallocLeak();   // calloc之后，不释放空间
        TestReallocLeak();  // realloc之后，不释放空间
        TestMemalignLeak(); // memalign之后，不释放空间
        TestNewLeak();      // new之后，不delete释放空间
        TestNewArrayLeak(); // new array 之后不释放空间
        TestContainerLeak();// new vector之后，不释放空间
    };

    for (int i = 0; i < NR_TEST_THREAD; i++) { // 创建十次
        std::thread m_thread(leak_test); // 创建一个test_thread对象,
        m_thread.detach(); //允许线程独立于线程句柄执行
    }

    return TestJavaRefNative(); // java层拿到本地创建的空间
}

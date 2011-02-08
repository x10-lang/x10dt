#ifndef __X10_COMPILER_WS_JAVA_STOLEN_H
#define __X10_COMPILER_WS_JAVA_STOLEN_H

#include <x10rt.h>


#define X10_LANG_RUNTIMEEXCEPTION_H_NODEPS
#include <x10/lang/RuntimeException.h>
#undef X10_LANG_RUNTIMEEXCEPTION_H_NODEPS
namespace x10 { namespace compiler { namespace ws { namespace java { 

class Stolen : public x10::lang::RuntimeException   {
    public:
    RTT_H_DECLS_CLASS
    
    void _instance_init();
    
    static x10aux::ref<x10::compiler::ws::java::Stolen> FMGL(STOLEN);
    
    static void FMGL(STOLEN__do_init)();
    static void FMGL(STOLEN__init)();
    static volatile x10aux::status FMGL(STOLEN__status);
    static inline x10aux::ref<x10::compiler::ws::java::Stolen> FMGL(STOLEN__get)() {
        if (FMGL(STOLEN__status) != x10aux::INITIALIZED) {
            FMGL(STOLEN__init)();
        }
        return x10::compiler::ws::java::Stolen::FMGL(STOLEN);
    }
    static x10aux::ref<x10::lang::Reference> FMGL(STOLEN__deserialize)(x10aux::deserialization_buffer &buf);
    static const x10aux::serialization_id_t FMGL(STOLEN__id);
    
    void _constructor();
    
    static x10aux::ref<x10::compiler::ws::java::Stolen> _make();
    
    virtual x10aux::ref<x10::compiler::ws::java::Stolen> x10__compiler__ws__java__Stolen____x10__compiler__ws__java__Stolen__this(
      );
    
    // Serialization
    public: static const x10aux::serialization_id_t _serialization_id;
    
    public: virtual x10aux::serialization_id_t _get_serialization_id() {
         return _serialization_id;
    }
    
    public: virtual void _serialize_body(x10aux::serialization_buffer& buf);
    
    public: template<class __T> static x10aux::ref<__T> _deserializer(x10aux::deserialization_buffer& buf);
    
    public: void _deserialize_body(x10aux::deserialization_buffer& buf);
    
};

} } } } 
#endif // X10_COMPILER_WS_JAVA_STOLEN_H

namespace x10 { namespace compiler { namespace ws { namespace java { 
class Stolen;
} } } } 

#ifndef X10_COMPILER_WS_JAVA_STOLEN_H_NODEPS
#define X10_COMPILER_WS_JAVA_STOLEN_H_NODEPS
#include <x10/lang/RuntimeException.h>
#ifndef X10_COMPILER_WS_JAVA_STOLEN_H_GENERICS
#define X10_COMPILER_WS_JAVA_STOLEN_H_GENERICS
template<class __T> x10aux::ref<__T> x10::compiler::ws::java::Stolen::_deserializer(x10aux::deserialization_buffer& buf) {
    x10aux::ref<x10::compiler::ws::java::Stolen> this_ = new (memset(x10aux::alloc<x10::compiler::ws::java::Stolen>(), 0, sizeof(x10::compiler::ws::java::Stolen))) x10::compiler::ws::java::Stolen();
    buf.record_reference(this_);
    this_->_deserialize_body(buf);
    return this_;
}

#endif // X10_COMPILER_WS_JAVA_STOLEN_H_GENERICS
#endif // __X10_COMPILER_WS_JAVA_STOLEN_H_NODEPS
